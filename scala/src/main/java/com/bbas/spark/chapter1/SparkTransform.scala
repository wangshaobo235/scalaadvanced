package com.bbas.spark.chapter1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.control.Breaks.{break, breakable}

object SparkTransform {
  def main(args: Array[String]): Unit = {
    //TODO
    // transform 算子：map,filter,flatmap等，懒执行。
    // action 算子：collect,take,saveAsTextFile等
    // 转换算子不会立即执行，只有当执行 行动算子的时候才会触发转换算子执行
    // 声明spark程序入口
    val transform: SparkConf = new SparkConf().setMaster("local").setAppName("transform")
    val context = new SparkContext(transform)

    // TODO map算子
    /*
    val strings: List[String] = List("abc", "bcd", "lol", "dog")
    context.parallelize(strings).map(x => x.toUpperCase).foreach(x => println(x))
    println("#########")
    context.parallelize(strings).map(x => (x, 1)).foreach(x => println(x._1 + " " + x._2))
    println("#########")
    context.parallelize(strings).map(x => {
      if (x.startsWith("a")) {
        (x, 1)
      } else if (x.startsWith("b")) {
        (x, 2)
      } else {
        (x, 3)
      }
    }).foreach(x => println(x._1 + " " + x._2))
    println("#########")
    val strings1: List[String] = List("1,zhangsan,male,20,shanghai", "2,lisi,famle,30,shijiazhuang")

    val value: RDD[Student] = context.parallelize(strings1).map(
      x => {
        new Student(x.split(",")(0).toInt, x.split(",")(1), x.split(",")(2), x.split(",")(3).toInt, x.split(",")(4))
      }
    )

    value.foreach(x => println(x.id + " " + x.age + " " + x.sex + " " + x.name + " " + x.location))

     */

    // TODO filter 算子

    val value: RDD[String] = context.textFile("datafiles/datatextfile.txt")
    val value1: RDD[(Int, String)] = value.map(x => {
      (x.split(",")(0).toInt, x)
    })
    val value2: RDD[(Int, String)] = value1.filter(x => {
      x._1 % 2 == 0
    })

    val value3: RDD[String] = value.filter(x => {
      x.split(",")(0).toInt % 2 == 0
    })

    value2.foreach(x => println(x))
    println("#########")
    value3.foreach(x => println(x))


    //加载文件，过滤掉存在空字段的行
    val value4: RDD[String] = context.textFile("datafiles/datatextfile.txt")
    val value5: RDD[Array[String]] = value4.map(x => x.split(","))
    println("#########")
    val value6: RDD[Array[String]] = value5.filter(x => {
      var flag = true
      breakable {
        for (i <- 0 to x.length - 1) {
          if (x(i).trim.length == 0) {
            flag = false
            break()
          }
          else true
        }
      }
      flag
    })

    value6.foreach(x => {
      for (i <- 0 to x.length - 1) {
        print(x(i) + " ")
      }
      println()
    }
    )


    //TODO 加载wordcount 测试文件成为一个rdd
    /*然后对rdd的字符串进行切割，得到新的rdd
    对数组rdd进行过滤：
    1.数组元素小于8的不要
    2.数组以H开头的不要
    3.数组中存在某个元素长度>6的不要
     */
    println("加载wordcount 测试文件成为一个rdd")
    val value7: RDD[Array[String]] = context.textFile("datafiles/datatextfile.txt").map(x => x.split(","))
    val value8: RDD[Array[String]] = value7.filter(x => {
      /*var flag = true
      breakable {
        for (i <- 0 to x.length - 1) {
          if (x(i).trim.length > 6) {
            flag = false
            break()
          }
        }
      }

       */
      if (x.length < 8 || x(0) == "H" || x.exists(x => x.length > 6))
        false
      else
        true
    })
    value8.foreach(x => {
      for (i <- 0 to x.length - 1) {
        print(x(i) + " ")
      }
      println()
    }
    )





    //TODO spark程序入口关闭
    context.stop()
  }

}

case class Student(id: Int, name: String, sex: String, age: Int, location: String)
