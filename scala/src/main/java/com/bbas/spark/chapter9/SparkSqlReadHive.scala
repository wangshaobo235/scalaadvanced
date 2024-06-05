package com.bbas.spark.chapter9

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlReadHive {
  def main(args: Array[String]): Unit = {

    //TODO SPARKSQL访问hive；
    //  1.hive-site.xml 主要是指hive的元数据服务地址
    //  2.pom.xml 需要加入spark-hive 这个整合包
    //  3.SparkSession还要开启hive支持开关
    //  4.hive版本 3.1.3 spark 3.1.3 hive 2.12.18 hadoop 3.2.4
    /*
    import org.apache.spark.sql.SparkSession
    // 创建SparkSession，确保启用Hive支持
    val spark = SparkSession
      .builder()
      .appName("WriteToHiveExample")
      .config("spark.sql.warehouse.dir", "<your_hive_warehouse_directory>")
      .enableHiveSupport()
      .getOrCreate()

    // 使用Spark SQL创建一个DataFrame
    val data = Seq(("Alice", 1), ("Bob", 2)).toDF("name", "id")

    // 注册为临时表
    data.createOrReplaceTempView("people")

    // 将数据写入Hive表，如果表不存在则创建
    spark.sql("CREATE TABLE IF NOT EXISTS default.hive_table (name STRING, id INT)")

    // 将临时表数据插入到Hive表
    spark.sql("INSERT INTO TABLE default.hive_table SELECT * FROM people")

    // 关闭SparkSession
    spark.stop()
     */
    val conf: SparkConf = new SparkConf().setMaster("local").setAppName("readhive").set("spark.default.parallsilm", "20")
      .set("spark.sql.shuffle.partitions", "10")
    val session: SparkSession = SparkSession.builder()
      .config(conf)
      .config("hive.metastore.uris", "thrift://master:9083") //如果配置文件无法加载，就在代码中进行指定
      .enableHiveSupport()
      .getOrCreate()

    val frame: DataFrame = session.sql(
      """
        |
        |""".stripMargin)


    frame.printSchema()

    session.read.table("")

    //读取hive分区表
    session.sql(
      """
        |select * from tablename where col_partition=''
        |""".stripMargin)


    // TODO hive写数据 1. dataframe api write 2.纯sql形式
    frame.write.saveAsTable("") //如果指定的表不存在，spark会自动创建



    //纯sql方式
    frame.createTempView("frame")
    session.sql(
      """
        |insert into table tablename
        |select * from frame
        |""".stripMargin)

    session.close()


  }

}
