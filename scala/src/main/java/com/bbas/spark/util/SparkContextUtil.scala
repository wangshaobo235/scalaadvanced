package com.bbas.spark.util

import org.apache.spark.{SparkConf, SparkContext}

object SparkContextUtil {

  def getSparkContext():SparkContext={
    new SparkContext(new SparkConf().setMaster("local").setAppName("sparkcontext"))
  }

}
