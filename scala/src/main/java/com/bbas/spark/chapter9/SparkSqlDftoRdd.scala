package com.bbas.spark.chapter9

import com.bbas.spark.util.SparkContextUtil
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Encoders, SparkSession}

object SparkSqlDftoRdd {
  def main(args: Array[String]): Unit = {
    //TODO dataframe,dataset与rdd之间的转换
    /*
    把已有的rdd转换成dataframe和dataset
    sparksession中拥有工具方法
    sparksession将一个rdd生成dataset时，需要传入encoder(编译器),用于从rdd的元素对象中，通过反射方式生成schema。
    将rdd[T]转换成dataset[T],就需要类似的encoder[T]
    spark中内置了大量常用的类型
     */
    val session: SparkSession = SparkContextUtil.getSparkSession()
    import session.implicits._
    val value: RDD[(Int, String)] = session.sparkContext.parallelize(Seq((1, "test"), (2, "ceshi"), (3, "haha")))
    session.createDataset(value)


    session.close()
  }

}
