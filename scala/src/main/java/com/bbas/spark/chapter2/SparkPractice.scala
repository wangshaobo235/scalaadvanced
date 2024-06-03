package com.bbas.spark.chapter2

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkPractice {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val sparkPractice: SparkConf = new SparkConf().setMaster("local").setAppName("sparkPractice")
    val context = new SparkContext(sparkPractice)
    val hero: RDD[Array[String]] = context.textFile("datafiles/datatextfile.txt").map(x => x.split(","))
    val info: RDD[Array[String]] = context.textFile("datafiles/battle.txt").map(x => x.split(","))

    //TODO 每个城市，kill,关押的人数
    val value: RDD[(String, String)] = hero.map(x => (x(0), x(3)))
    val value1: RDD[(String, Array[String])] = info.map(x => {
      val tuple: (String, Array[String]) = (x(0), x)
      tuple
    })

    val value2: RDD[(String, (String, Array[String]))] = value.join(value1)
    val value3: RDD[(String, Int)] = value2.map(x => {
      (x._2._1 + "-" + x._2._2(1), x._2._2(2).toInt)
    })
    value3.reduceByKey(_ + _).foreach(x => println(x._1 + " " + x._2))

    context.stop()
  }

}
