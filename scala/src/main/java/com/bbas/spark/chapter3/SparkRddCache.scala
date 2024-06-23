package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

object SparkRddCache {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[String] = context.textFile("datafiles/wordcount.txt")
    val value1: RDD[(String, Int)] = value.flatMap(x => {
      x.split(" ")
    }).map(x => {
      (x, 1)
    })
    // cache 本质上是RDD运算出来的结果数据进行物化
    /*
    cache 默认调的是persist()
      def cache(): this.type = persist()
      def persist(): this.type = persist(StorageLevel.MEMORY_ONLY)

      case "NONE" => NONE
    case "DISK_ONLY" => DISK_ONLY
    case "DISK_ONLY_2" => DISK_ONLY_2
    case "DISK_ONLY_3" => DISK_ONLY_3
    case "MEMORY_ONLY" => MEMORY_ONLY
    case "MEMORY_ONLY_2" => MEMORY_ONLY_2
    case "MEMORY_ONLY_SER" => MEMORY_ONLY_SER
    case "MEMORY_ONLY_SER_2" => MEMORY_ONLY_SER_2
    case "MEMORY_AND_DISK" => MEMORY_AND_DISK
    case "MEMORY_AND_DISK_2" => MEMORY_AND_DISK_2
    case "MEMORY_AND_DISK_SER" => MEMORY_AND_DISK_SER
    case "MEMORY_AND_DISK_SER_2" => MEMORY_AND_DISK_SER_2
    case "OFF_HEAP" => OFF_HEAP

      
     */
    value1.cache()
    value1.persist(StorageLevel.MEMORY_ONLY)
    value1.unpersist()


    // 求每个单词的出现次数
    val value2: RDD[(String, Int)] = value1.reduceByKey(_ + _)

    // 将word这个rdd中的kv对，按key分组，然后将value一字符川形式拼接
    val value3: RDD[(String, Iterable[Int])] = value1.groupByKey()
    val value4: RDD[(String, String)] = value3.mapValues(x => x.mkString(","))
    value4.foreach(x => println(x))
    context.stop()

  }

}
