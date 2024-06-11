package com.bbas.spark.chapter9

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.expressions.{Window, WindowSpec}
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlTableApi {
  def main(args: Array[String]): Unit = {


    //TODO dataframe的 tableapi 字段命名，查询 api

    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val session: SparkSession = SparkContextUtil.getSparkSession()

    import session.implicits._

    //对一个df的字段进行命名
    val frame: DataFrame = session.read.csv("datafiles/battle.txt")
    frame.printSchema()
    val frame1: DataFrame = frame.toDF("id", "type", "cnt") // 给字段命名
    val frame2: DataFrame = frame1.withColumnRenamed("cnt", "person_cnt") // 修改某一类的字段名

    //查询api select

    frame2.select("id", "type")
    //    frame1.select("id*10") // select中传入的字符串只能是字段名，不能传表达式
    frame2.select(frame2("id"), frame2("person_cnt") * 10)
    frame2.select($"id", ($"person_cnt" * 10).alias("new_cnt")).show() // 计算之后的结果，调用.alias进行重命名
    frame2.select('id, ('person_cnt * 10).alias("new_cnt")).show() //

    //为了得到column对象,导入函数包，col() 等于 Column()
    import org.apache.spark.sql.functions._
    frame2.select(col("id"), col("person_cnt") * 10 as "new_cnt")

    frame2.selectExpr("id", "person_cnt*10 as new_person").show()


    println(
      """
        |********************
        |tableapi之group by
        |********************
        |""".stripMargin)

    //TODO dataframe的 tableapi 聚合 group by
    /**
     * where
     */

    frame2.where("person_cnt<20")
    frame2.where(col("person_cnt") > 20).show()

    /**
     * group by
     * agg : avg, max, min, sum, count
     */
    frame2.groupBy(col("id"))
      .agg(sum(col("person_cnt")) as "test",
        collect_list(col("person_cnt") as "list"))
      .show()


    //TODO dataframe tableapi
    /**
     * 窗口函数：
     * 店铺月累计金额
     */
    """
      |###############
      |窗口函数计算
      |###############
      |""".stripMargin

    val frame3: DataFrame = session.read.option("header", true).csv("datafiles/sales.txt")
    // shop_id,dt,sale_amount
    val frame4: DataFrame = frame3.groupBy($"shop_id", date_format(($"dt"), "yyy-MM") as "month").agg(sum(col("sale_amount")) as "month_total")
      .select("shop_id", "month", "month_total")


    frame4.printSchema()
    val spec: WindowSpec = Window.partitionBy(col("shop_id")).orderBy(col("month").asc).rowsBetween(Window.unboundedPreceding, Window.currentRow)
    frame4.withColumn("leiji_amount", sum("month_total").over(spec))
      .select("shop_id", "month", "month_total", "leiji_amount").show()


    /**
     * join
     *
     * jointype
     * inner, cross, outer, full, fullouter, full_outer, left, leftouter, left_outer, right, rightouter,
     * right_outer, semi, leftsemi, left_semi, anti, leftanti, left_anti.
     */
    println(
      """
        |###############
        |join计算
        |###############
        |""".stripMargin)

    val frame5: DataFrame = session.read.option("header", true).csv("datafiles/sales.txt")
    val frame6: DataFrame = session.read.option("header", true).csv("datafiles/salesinfo.txt")
    frame5.crossJoin(frame6)
    frame5.join(frame6)
    frame5.join(frame6, Seq("shop_id")) // 必须两个dataframe都包含这个shop_id字段
    frame5.join(frame6, frame5("shop_id") === frame6("shop_id")) //
    frame5.alias("frame5").
      join(frame6.alias("frame6"),
        (frame5("shop_id") === frame6("shop_id")) && (1 == 1), // 定义关联条件
        "right") // 定义关联方式
      .select(frame6("shop_id")).distinct().show() //


    /**
     * union
     * tableapi中的union就是sql中的union all
     */
    println(
      """
        |###############
        |union计算
        |###############
        |""".stripMargin)


    val frame7: DataFrame = session.read.option("header", true).csv("datafiles/salesinfo")
    val frame8: DataFrame = session.read.option("header", true).csv("datafiles/salesinfo")

    frame7.union(frame8) // 不去重
    session.close()

  }

}
