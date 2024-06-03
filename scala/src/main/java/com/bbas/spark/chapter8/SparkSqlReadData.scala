package com.bbas.spark.chapter8

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlReadData {
  def main(args: Array[String]): Unit = {
    // TODO sparksql csv
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("base")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    val frame: DataFrame = session.read
      // .option("inferSchema",true) 推荐各个数据列的类型
      // .option("header","false") // 定义csv或txt文件 是否包含header
      .csv("datafiles/datacsv.txt")

    frame.printSchema()
    frame.show()

    //手动指定数据列+数据类型
    val structType: StructType = StructType(Seq(
      StructField("id", DataTypes.IntegerType),
      StructField("name", DataTypes.StringType),
      StructField("age", DataTypes.IntegerType),
      StructField("income", DataTypes.DoubleType)
    ))

    val frame1: DataFrame = session.read
      // .option("inferSchema",true) 推荐各个数据列的类型
      // .option("header","false") // 定义csv或txt文件 是否包含header
      .schema(structType)
      .csv("datafiles/datacsv.txt")

    frame1.printSchema()

    frame1.write.parquet("datafiles/write/parquet")

    frame1.createTempView("csvtable")

    val frame2: DataFrame = session.sql(
      """
        |select sum(age) as sum_age from csvtable
        |""".stripMargin)

    frame2.show()

    session.catalog.dropTempView("csvtable")

    session.close()
  }

}
