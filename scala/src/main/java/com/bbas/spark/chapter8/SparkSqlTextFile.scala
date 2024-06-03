package com.bbas.spark.chapter8

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SparkSqlTextFile {
  def main(args: Array[String]): Unit = {
    //TODO 读取json格式数据，如何查看解析数据，如果过滤无法解析的数据
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("base")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    import session.implicits._

    // dataframe 等于 Dataset[Row]
    val frame: DataFrame = session.read.text("datafiles/battle.txt")
    val value1: Dataset[String] = frame.flatMap(row => {
      row.getAs[String]("value").split(",")
    })

    value1.printSchema()

    println("#######")

    frame.printSchema()
    frame.createTempView("frame")
    session.sql(
      """
        |select explode(split(value,',')) from frame
        |""".stripMargin).show()
    val value: Dataset[String] = session.read.textFile("datafiles/battle.txt")
    value.printSchema()
    session.close()
  }

}
