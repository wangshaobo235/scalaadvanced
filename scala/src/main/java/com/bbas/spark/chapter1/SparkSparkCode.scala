package com.bbas.spark.chapter1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * ClassName: SparkSprkCode
 * Package: com.bbas.spark.chapter1
 * Description:
 *
 * @Author 王少波
 * @Create 2024/5/24 17:47
 * @Version 1.0
 */
object SparkSparkCode {
  def main(args: Array[String]): Unit = {
    //TODO spark程序的运行模式：
    // local 运行(可linux，window,主要用于测试)
    // 分布式运行(先将程序开发好，然后提交到集群上分布式运行)
    // 1、standalone 集群(spark自带的一个资源调度集群)运行  2、yarn集群运行 3、mesos集群运行  4、k8s容器化运行

    //TODO WordCount
    // 构建一个spark编程入口对象
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Wordcount")
    val sc = new SparkContext(conf)
    // 加载数据集
    val value: RDD[String] = sc.textFile("datafiles/data.txt")

    // 调用各种转换算子 transform
    /*val value1: RDD[String] = value.flatMap(x => x.split(" "))
    val value2: RDD[(String, Int)] = value1.map(x => (x, 1))
    val value3: RDD[(String, Int)] = value2.reduceByKey(_ + _)
    value3.foreach(x => println(x._1 + " " + x._2))
     */

    val value1: RDD[Array[String]] = value.map(x => x.split(" "))
    val value2: RDD[(String, Int)] = value1.flatMap(x => x).map(x => (x, 1))
    val value3: RDD[(String, Int)] = value2.reduceByKey(_ + _)
    // value3.foreach(x => println(x._1 + " " + x._2))
    sc.stop()

    // 触发执行算子 action
    value3.foreach(x => println(x._1 + " " + x._2))

  }

}
