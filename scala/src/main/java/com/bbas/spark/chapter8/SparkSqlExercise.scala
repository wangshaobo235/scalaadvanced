package com.bbas.spark.chapter8

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlExercise {
  def main(args: Array[String]): Unit = {
    //TODO 计算 shopid,月份，月销售额，累计到该月的销售金额
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("exercise")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    val structType: StructType = StructType(Seq(StructField("id", DataTypes.StringType), StructField("dt", DataTypes.DateType), StructField("amount", DataTypes.DoubleType)))

    val frame: DataFrame = session.read.option("header", true).schema(structType).csv("datafiles/sales.txt")

    frame.printSchema()

    frame.createTempView("saledata")
    // rows between unbounded preceding and current row 窗口函数
    session.sql(
      """
        |select info.id,info.month,info.amount,sum(info.amount)over(partition by info.id order by info.month ) leiji_amount from
        |(
        |select id,date_format(dt,'yyyy-MM') month,sum(amount) amount from saledata
        |group by id,date_format(dt,'yyyy-MM')
        |) info
        |""".stripMargin).show()

    session.catalog.dropTempView("frame")
    session.close()


  }

}
