package com.bbas.spark.chapter9

import com.bbas.spark.util.SparkContextUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, Row, SparkSession}

case class person(id: Int, name: String)

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
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val session: SparkSession = SparkContextUtil.getSparkSession()
    import session.implicits._ // 必须导入隐式转换
    val value: RDD[(Int, String)] = session.sparkContext.parallelize(Seq((1, "test"), (2, "ceshi"), (3, "haha")))
    val value1: Dataset[(Int, String)] = session.createDataset(value)
    value1.printSchema()
    //dataset上只要执行了sql算子，就会转换成dataframe
    val frame: DataFrame = value1.toDF("id", "name")
    frame.printSchema()


    val frame1: DataFrame = session.createDataFrame(value).toDF("id", "name")
    frame1.printSchema()


    //TODO dataframe和dataset转回rdd
    val rdd: RDD[Row] = frame1.rdd // dataframe转回rdd,类型一定是Row 因为dataframe等于dataset[Row]
    val rdd1: RDD[(Int, String)] = value1.rdd // ds转回rdd,会保留原来的元素类型
    //dataset与dataframe互转
    value1.toDF() // dataset转换为dataframe
    val value2: Dataset[person] = frame1.as[person] // dataframe转换为dataset
    value2.printSchema()

    //TODO dataset或者dataframe调rdd算子
    val value3: Dataset[(Int, String)] = value2.map(x => (x.id, x.name)) // 将dataset[Person]转换为dataset[(Int,String)]




    session.close()
  }

}
