package com.bbas.spark.chapter3

import com.bbas.spark.util.SparkContextUtil
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkAction {
  def main(args: Array[String]): Unit = {
    //TODO action 算子：collect,take,saveAsTextFile等
    // 转换算子不会立即执行，只有当执行 行动算子的时候才会触发转换算子执行
    // 声明spark程序入口
    val context: SparkContext = SparkContextUtil.getSparkContext()
    val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    //reduce 将rdd中所有的数据聚合成一个数
    println(value.reduce(_ + _))
    // count 计算rdd中数据的条数
    value.count()
    // first 取rdd中的第一条数据
    value.first()
    //take(n) 取rdd中n条数据
    value.take(2)
  }

}
