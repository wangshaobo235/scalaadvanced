package com.bbas.spark.chapter10

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlUdf {
  def main(args: Array[String]): Unit = {
    //TODO 自定义UDF
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkContextUtil.getSparkSession()
    import session.implicits._
    // 在sparksql的catalog中，注册函数名
    session.udf.register("firstUpper", (name: String) => name.substring(0, 1).toUpperCase() + name.substring(1))

    val frame: DataFrame = session.read.option("header", true).csv("datafiles/battle.txt").toDF("id", "type", "number")

    frame.selectExpr("firstUpper(type)").show()

    session.close()

  }

}
