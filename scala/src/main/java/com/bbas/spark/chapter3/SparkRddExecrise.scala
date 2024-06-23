package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SparkRddExecrise {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[String] = context.textFile("datafiles/order.txt")
    val value1: RDD[(String, Int)] = value
      .map(x => {
        val strings: Array[String] = x.split("，")
        (strings(1), strings(2).toInt)
      })
      .reduceByKey(_ + _)
      .sortBy(x=>x._2,false)
    value1.foreach(x=>println(x))

    context.stop()
  }

}
