package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable

object SparkRddClosure {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[(Int, String)] = context.parallelize(Seq((1, "北京"), (2, "上海"), (3, "石家庄")))

    //map 对象是在drivers端声明的
    val intToString: mutable.HashMap[Int, String] = new mutable.HashMap[Int, String]()
    intToString.put(1,"zhangsan")
    intToString.put(2,"lisi")
    // intToString.put(3,"wangwu")

    // rdd的计算在集群task程序中运行
    val value1: RDD[(Int, String, String)] = value.map(x => {
      //分布式算子如果引用外部变量，则driver会把该变化序列化后通过网络发送给每一个task
      (x._1, intToString.get(x._1).getOrElse("none"), x._2)
    })


    /*
    第一种 字典表映射为rdd,事实表与字段表进行关联 join,进行shuffle ,reduce端 join
    第二种 driver会把该变化序列化后通过网络发送给每一个task ，不需要shuffle ,类似于 map端 join
    ！！！ 闭包引用的目标对象必须是可以序列化的，而且数据量不可太大
    exector 为进程  task 为线程
    一个exector会创建多个task
    object 对象会在exector生成一份，多个task共享一个object
    普通对象会在每个task生成一份


    闭包函数内，对外部变量进行了修改 ，外部变量不会改变

    object extends seriable 的目的是为了可序列化，方便闭包

     */

    value1.foreach(x=>println(x))

    context.stop()




  }

}
