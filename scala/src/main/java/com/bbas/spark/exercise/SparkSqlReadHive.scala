package com.bbas.spark.exercise

import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object SparkSqlReadHive {
  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkSession.builder()
      .appName("sparkreadhive")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .enableHiveSupport()
      .getOrCreate()

    val structType: StructType = StructType(Seq(StructField("id", DataTypes.IntegerType), StructField("name", DataTypes.StringType), StructField("age", DataTypes.IntegerType)))

    val frame: DataFrame = session.read.schema(structType).csv(args(0))

    frame.createTempView("csv_table")

    frame.printSchema()

    session.sql(
      """
        |drop table if exists test.csv_table
        |""".stripMargin)

    session.sql(
      """
        |create table test.csv_table(
        |id int,
        |name string,
        |age int
        |)
        |row format delimited fields terminated by ','
        |stored as textfile
        |""".stripMargin)

    session.sql(
      """
        |insert into test.csv_table
        |select id ,name ,age from csv_table
        |""".stripMargin)

    session.catalog.dropTempView("csv_table")


    session.sql(
      """
        |select * from test.csv_table
        |""".stripMargin).show()

    session.close()
  }

}
