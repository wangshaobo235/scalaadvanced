package com.bbas.spark.chapter8

import com.sun.prism.PixelFormat.DataType
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSqlRead {
  def main(args: Array[String]): Unit = {

    //TODO 读取json格式数据，如何查看解析数据，如果过滤无法解析的数据
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)

    val session: SparkSession = SparkSession.builder()
      .master("local")
      .appName("base")
      .config("spark.default.parallesilm", 20)
      .config("spark.sql.shuffle.partitions", 1)
      .getOrCreate()

    import session.implicits._ // 导入spark的隐式转换

    val frame: DataFrame = session.read.json("datafiles/data.json")

    import org.apache.spark.sql.functions._
    frame.filter($"_corrupt_record".isNotNull)
    frame.filter('_corrupt_record.isNotNull)
    /*
    +---------------------+----+----+----+
    |      _corrupt_record| age|  id|name|
    +---------------------+----+----+----+
    |                 null|  10|   1|张飞|
    |                 null|  20|   2|关羽|
    |                 null|  30|   3|刘备|
    |                 null|  40|   4|赵云|
    |{"id":5,"name":"赵...|null|null|null|
    +---------------------+----+----+----+

    无法解析的json数据，根据第一列进行判断 _corrupt_record为null，正常解析 ，非null为无法正常解析的数据

     */
    frame.show()

    frame.createTempView("jsontable")

    println("##### 干净数据 #####")

    session.sql(
      """
        |select * from jsontable
        |where _corrupt_record is null
        |""".stripMargin).show()

    frame.printSchema()

    println("嵌套json数据")
    /*
    root
   |-- age: array (nullable = true)
   |    |-- element: struct (containsNull = true)
   |    |    |-- guanxi: string (nullable = true)
   |    |    |-- name: string (nullable = true)
   |-- id: long (nullable = true)
   |-- name: string (nullable = true)
     */

    val frame1: DataFrame = session.read.json("datafiles/datasecond.json")
    frame1.printSchema()
    frame1.createTempView("frame1")
    session.sql("select name,id,age[0].guanxi from frame1").show()
    session.catalog.dropTempView("frame1")


    println("嵌套json数据,每个json key 不同")
    /*
    嵌套json数据,每个json key 不同
    root
     |-- age: struct (nullable = true)
     |    |-- education: string (nullable = true)
     |    |-- guanxi: string (nullable = true)
     |    |-- loc: string (nullable = true)
     |    |-- name: string (nullable = true)
     |    |-- phone: string (nullable = true)
     |-- id: long (nullable = true)
     |-- name: string (nullable = true)

    +----------------------+---+----+
    |                   age| id|name|
    +----------------------+---+----+
    |{null, father, 北京...|  1|张飞|
    |  {doctor, mother, ...|  2|关羽|
    |  {null, null, null...|  3|刘备|
    |  {null, uncle, nul...|  4|赵云|
    +----------------------+---+----+
     */
    val structType: StructType = StructType(Seq(StructField("id", DataTypes.IntegerType), StructField("name", DataTypes.StringType),
      StructField("age", DataTypes.createMapType(DataTypes.StringType, DataTypes.StringType))))
    val frame2: DataFrame = session.read.schema(structType).json("datafiles/datathird.json")
    frame2.printSchema()
    frame2.createTempView("frame2")
    session.sql("select age['name'] from frame2").show() // map： 根据key读取对应value
    session.catalog.dropTempView("frame2")

    session.close()
  }

}
