package com.bbas.spark.chapter9

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlSession {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("session")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .enableHiveSupport().getOrCreate()

    import session.implicits._

    //临时试图分为两种 会话级别和全局级别
    val frame: DataFrame = session.read.csv("datafiles/datacsv.txt")

    frame.createTempView("tablename")

    session.sql(
      """
        |select * from tablename
        |""".stripMargin).show()


    session.catalog.dropTempView("tablename")


    session.close()

  }

}
