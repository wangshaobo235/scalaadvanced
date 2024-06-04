package com.bbas.spark.chapter9

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSqlReadHive {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("readhive").set("spark.default.parallsilm", "20")
      .set("spark.sql.shuffle.partitions", "10")
    val session: SparkSession = SparkSession.builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()

    session.close()



  }

}
