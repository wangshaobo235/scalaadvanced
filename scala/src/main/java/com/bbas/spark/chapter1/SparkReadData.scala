package com.bbas.spark.chapter1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkReadData {
  def main(args: Array[String]): Unit = {
    // TODO spark读取数据方式：
    //  spark读取本地text文本文件
    val readdata: SparkConf = new SparkConf().setMaster("local").setAppName("readdata")
    val context = new SparkContext(readdata)
    val value: RDD[String] = context.textFile("datafiles/datatextfile.txt")

    //Rdd读取前几行
    val strings: Array[String] = value.take(2)

    strings.foreach(x => {
      println(x)
    })

    //TODO spark读取sequence文件
    //val value2: RDD[(Int, String)] = context.sequenceFile("datafiles/data.txt", classOf[Int], classOf[String])

    //TODO spark读取hdfs文件
    //val value1: RDD[String] = context.textFile("hdfs://master:8020/wordcount/path")


    //TODO 从mysql加载数据表 (后续从sparksql接口进行读取)


    //TODO 从本地集合进行数据加载 makeRDD parallelize
    val value1: RDD[String] = context.makeRDD(List("a", "b", "c")) // 看源码，实际调用parallelize
    val value2: RDD[String] = context.parallelize(List("a", "b", "c"))
    value1.foreach(x => println(x))

    val seq=Seq("zhangsan is a student","lisi is a teacher","wangwu is a doctor")
    val value3: RDD[String] = context.parallelize(seq)
    value3.flatMap(x=>{x.split(" ")}).map(x=>(x,1)).reduceByKey(_+_).foreach(x=>println(x._1+" "+x._2))

    
    context.stop()

  }

}
