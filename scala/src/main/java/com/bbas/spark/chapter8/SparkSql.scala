package com.bbas.spark.chapter8

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSql {
  def main(args: Array[String]): Unit = {

    // TODO sparksql的入门学习
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("base")
      .getOrCreate()

    val frame: DataFrame = session.read.option("header", true).csv("datafiles/datatextfile.txt")

    frame.printSchema()

    frame.createTempView("datatable")

    val frame1: DataFrame = session.sql(
      """
        |select * from datatable
        |""".stripMargin)

    frame1.show()

    session.catalog.dropTempView("datatable")

    session.close()
  }

}
