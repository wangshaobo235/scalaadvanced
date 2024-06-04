package com.bbas.spark.chapter9

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlReadHive {
  def main(args: Array[String]): Unit = {

    //TODO SPARKSQL访问hive；
    //  1.hive-site.xml 主要是指hive的元数据服务地址
    //  2.pom.xml 需要加入spark-hive 这个整合包
    //  3.SparkSession还要开启hive支持开关
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

    session.read.table("")

    //读取hive分区表
    session.sql(
      """
        |select * from tablename where col_partition=''
        |""".stripMargin)

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
