package com.bbas.spark.chapter6

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SparkRddCheckpoint {
  def main(args: Array[String]): Unit = {
    // 这个application job数为3个
    /*val sc: SparkContext =SparkContextUtil.getSparkContext()
    sc.setCheckpointDir("data/checkpoint/")
    val rdd: RDD[Int] = sc.parallelize(seq = 1 to 10000)
    val rdd2: RDD[(String, Int)] = rdd.map(i => (i + "", i))
    val rdd3: RDD[(String, Int)] = rdd2.map(tp => (tp._1, tp._2 * 10))
    /*
    最本质的区别在于Checkpoint将RDD计算出来存储在可靠的HDFS上，并且可以斩断依赖链，若出错可以直接通过赋值HDFS中的文件实现容错。
    Cache是将RDD存放至内存中，并且未斩断依赖链，若出错只能通过依赖链回溯上一级RDD，重放计算。
     */
    rdd3.cache()
    rdd3.checkpoint() //会额外新增一个job来计算rdd3的数据并存储到HDFS
    rdd3.reduceByKey(_ + _).count()
    rdd3.map(tp => (tp._1 + "_a", Math.max(tp._2, 100))).groupByKey().mapValues(iter => iter.max).count()
    Thread.sleep(Long.MaxValue)
    sc.stop()

     */
  }
}
