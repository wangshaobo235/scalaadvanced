package com.bbas.spark.util

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkContextUtil {
  def getSparkContext(): SparkContext = {
    new SparkContext(new SparkConf().setMaster("local").setAppName("sparkcontext"))
  }

  def getSparkSession(): SparkSession = {
    val sparkcontext: SparkConf = new SparkConf().setMaster("local").setAppName("sparkcontext")
    SparkSession.builder().config(sparkcontext).enableHiveSupport().getOrCreate()
  }
}
