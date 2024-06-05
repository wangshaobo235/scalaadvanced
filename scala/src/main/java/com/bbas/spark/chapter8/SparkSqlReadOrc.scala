package com.bbas.spark.chapter8

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.Properties

object SparkSqlReadOrc {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("base")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    import session.implicits._

    //TODO 从mysql读取数据
    /*
    每个城市战斗力最高的两位将军的信息
    总战力最高的前2座城市
     */
    /*
    val properties = new Properties()

     */

    // val frame: DataFrame = session.read.jdbc("jdbc:mysql://:/salesdb", "test_table", properties)

    val structType: StructType = StructType(Seq(
      StructField("id", DataTypes.IntegerType),
      StructField("country", DataTypes.StringType),
      StructField("name", DataTypes.StringType),
      StructField("battle", DataTypes.IntegerType),
      StructField("age", DataTypes.IntegerType)))
    val frame1: DataFrame = session.read.option("header", true).schema(structType).csv("datafiles/sanguo.txt")

    // frame.createTempView("frame")
    frame1.createTempView("frame1")

    session.sql(
      """
        |select result.* from
        |(
        |select f1.*,row_number()over(partition by f.city order by f1.battle desc) as rn_cnt from frame f join frame1 f1
        |on f.id = f1.id
        |) result
        |where result.rn_cnt<=2
        |""".stripMargin).show()


    session.sql(
      """
        |select * from frame where city in (
        |select f.city from frame f join frame1 f1
        |on f.id = f1.id
        |group by f.city
        |order by sum(f1.battle) desc
        |limit 2)
        |""".stripMargin).show()

    session.catalog.dropTempView("frame")
    session.catalog.dropTempView("frame1")

    session.close()


  }

}
