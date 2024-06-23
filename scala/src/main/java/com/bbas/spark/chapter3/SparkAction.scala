package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkAction {
  def main(args: Array[String]): Unit = {
    //TODO action 算子：collect,take,saveAsTextFile等
    // 转换算子不会立即执行，只有当执行 行动算子的时候才会触发转换算子执行
    // 声明spark程序入口，自定义工具类
    // collect 将rdd中的数据汇总到driver端，collect慎用，一个rdd中的数据庞大，汇总到driver端容易内存溢出
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    val value1: RDD[String] = context.parallelize(Seq("a b c d","e f g h","h a b c"))

    //reduce 将rdd中所有的数据聚合成一个数 返回long类型
    println(value.reduce(_ + _)) //55
    // count 计算rdd中数据的条数 返回值long类型
    println(value.count()) // 10
    // first 取rdd中的第一条数据
    println(value.first()) //
    //take(n) 取rdd中n条数据
    value.take(2).foreach(x => println(x))

    //count
    println("***** count *****")
    val stringToLong: collection.Map[String, Long] = value1.flatMap(x => x.split(" ")).map(x => (x, 1))
      .countByKey()
    for (elem <- stringToLong) {
      println(elem)
    }

    // value.foreachPartition()


  }

}
