package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD

import scala.collection.mutable

object SparkRddBroadcast {
  def main(args: Array[String]): Unit = {
    //TODO 广播变量
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[(Int, String)] = context.parallelize(Seq((1, "北京"), (2, "上海"), (3, "石家庄")))

    //map 普通对象是在drivers端声明的
    val intToString: mutable.HashMap[Int, String] = new mutable.HashMap[Int, String]()
    intToString.put(1, "zhangsan")
    intToString.put(2, "lisi")
    intToString.put(3, "wangwu")

    //将driver端创建的普通集合对象，广播出去
    // 广播的实质：将driver端数据对象，序列化后，给每个exector发送一份（每个exector只持有一份广播变量的拷贝）
    // 广播变量的数据传输和闭包引用的数据传输有所不同。
    // 闭包引用的数据，是driver给每个executor直接发送数据
    // 广播变量的数据，是通过bittorrent协议来发送数据的
    // 广播变量主要应用于需要map端join的场合
    // 就是一份小体量的数据，直接让每个executor持有一份数据，在task的计算逻辑中直接可用

    // 相关面试题：spark如何实现map端join(闭包，广播变量，缓存文件[context.addfile()])
    val value1: Broadcast[mutable.HashMap[Int, String]] = context.broadcast(intToString)

    value.map(x => {
      val value2: mutable.HashMap[Int, String] = value1.value
      (x._1, value2.getOrElseUpdate(x._1, "none"), x._2)
    })

  }

}
