package com.bbas.spark.chapter9

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.expressions.{Window, WindowSpec}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSqlExercise {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val session: SparkSession = SparkContextUtil.getSparkSession()
    // user_id | age | gender | occupation | zip_code
    val structTypeUser: StructType = StructType(Seq(StructField("user_id", DataTypes.IntegerType),
      StructField("age", DataTypes.IntegerType),
      StructField("gender", DataTypes.StringType),
      StructField("occupation", DataTypes.StringType),
      StructField("zip_code", DataTypes.StringType)))
    val user: DataFrame = session.read.schema(structTypeUser).option("delimiter", "|").csv("datafiles/practice/u.user")
    println("***** user.data *****")
    user.show(10, true)

    // user_id | item_id | rating | timestamp.
    val structTypeData: StructType = StructType(Seq(StructField("user_id", DataTypes.IntegerType),
      StructField("item_id", DataTypes.IntegerType),
      StructField("rating", DataTypes.IntegerType),
      StructField("timestamp", DataTypes.StringType)))
    val data: DataFrame = session.read.schema(structTypeData).option("delimiter", "\t").csv("datafiles/practice/u.data")
    println("***** data.data *****")
    data.show(10)
    /** movie_id | movie_title | release_date | video_release_date |
     * IMDb_URL | unknown | Action | Adventure | Animation |
     * Children's | Comedy | Crime | Documentary | Drama | Fantasy |
     * Film-Noir | Horror | Musical | Mystery | Romance | Sci-Fi |
     * Thriller | War | Western |
     */
    val structTypeItem: StructType = StructType(Seq(StructField("movie_id", DataTypes.IntegerType),
      StructField("movie_title", DataTypes.StringType),
      StructField("release_date", DataTypes.StringType),
      StructField("video_release_date", DataTypes.StringType),
      StructField("IMDb_URL", DataTypes.StringType),
      StructField("unknown", DataTypes.StringType),
      StructField("Action", DataTypes.StringType),
      StructField("Adventure", DataTypes.StringType),
      StructField("Animation", DataTypes.StringType),
      StructField("Children's", DataTypes.StringType),
      StructField("Comedy", DataTypes.StringType),
      StructField("Crime", DataTypes.StringType),
      StructField("Documentary", DataTypes.StringType),
      StructField("Drama", DataTypes.StringType),
      StructField("Fantasy", DataTypes.StringType),
      StructField("Film-Noir", DataTypes.StringType),
      StructField("Horror", DataTypes.StringType),
      StructField("Musical", DataTypes.StringType),
      StructField("Mystery", DataTypes.StringType),
      StructField("Romance", DataTypes.StringType),
      StructField("Sci-Fi", DataTypes.StringType),
      StructField("Thriller", DataTypes.StringType),
      StructField("War", DataTypes.StringType),
      StructField("Western", DataTypes.StringType)))
    val item: DataFrame = session.read.schema(structTypeItem).option("delimiter", "|").csv("datafiles/practice/u.item")
    println("***** item.data *****")
    item.show(10)
    import session.implicits._
    import org.apache.spark.sql.functions._
    //过滤掉年龄小于10岁的数据
    user.where($"age" > 10).createTempView("user")
    //对存入hive的三张表
    data.createTempView("data")
    item.createTempView("item")
    //报表1：平均得分最高的100部电影
    println("***** 平均得分最高的100部电影 *****")
    val report1: Dataset[Row] = data.join(item, data("item_id") === item("movie_id")).groupBy(item("movie_id"))
      .agg(avg(data("rating")) as "avg_rate")
      .orderBy($"avg_rate".desc).limit(100)
    //报表2：评分次数最高的100部电影
    val report2: Dataset[Row] = data.join(item, data("item_id") === item("movie_id")).groupBy(item("movie_id"))
      .agg(count(data("item_id")) as "movie_cnt")
      .orderBy($"movie_cnt".desc).limit(100)
    //每种风格下，平均得分最高的10部电影
    println("***** 每种性别下，平均得分最高的10部电影 *****")
    val spec: WindowSpec = Window.partitionBy().orderBy().rowsBetween(Window.unboundedPreceding, Window.unboundedFollowing)
    user.join(data, user("user_id") === data("user_id"))
      .groupBy(user("gender"))
      .agg(count(data("item_id")) as "num")
      .select("gender", "num").show()
    // 利用窗口函数
    user.join(data, user("user_id") === data("user_id")).withColumn("leiji", avg(data("rating")) over (spec))
    //报表3：每种性别的观众，观看最多的3种电影风格
    println("***** 每种性别的观众，观看最多的3种电影风格 *****")
    user.join(data, user("user_id") === data("user_id"))
      .groupBy(user("gender"))
      .agg(count(data("item_id")) as "num")
      .select("gender", "num").show()

    session.close()

  }

}
