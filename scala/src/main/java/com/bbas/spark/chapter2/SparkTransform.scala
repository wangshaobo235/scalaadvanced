package com.bbas.spark.chapter2

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkTransform {
  def main(args: Array[String]): Unit = {
    // 初始化spark对象入口
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val transForm: SparkConf = new SparkConf().setMaster("local").setAppName("transForm")
    val context = new SparkContext(transForm)
    //TODO mappartitions
    // setMaster("local[*]") 控制defaultparallelism parallelize(seq: Seq[T],numSlices: Int = defaultParallelism))
    /*
     context.parallelize(Seq(1, 2, 3, 4)).map(x => {
       println("map")
       (x, 1)
     }).foreach(x => println(x))

     //mapPartitions 一次性将分区数据以迭代器方式传入
     context.parallelize(Seq(1, 2, 3, 4)).mapPartitions(x => {
       println("map")
       x.map(y => (y, 1))
     }
     ).foreach(x => println(x))

     */


    //TODO distinct
    /*val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 1, 2, 3))
    value.distinct().foreach(x => println(x))

     */

    //TODO groupByKey 传入元组，生成(key,iterable[]) 类型
    /*val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 1, 2, 3))
    val value1: RDD[(Int, Iterable[Int])] = value.map(x => (x, 1)).groupByKey()
    value1.foreach(x=>{
      println(x._1+" "+x._2.sum)
    })

     */


    //TODO groupByKey 传入元组，生成(key,iterable[]) 类型
    //val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 1, 2, 3))
    //value.map(x => (x, 1)).reduceByKey(_+_).foreach(x=>println(x._1+" "+x._2))


    //TODO sortByKey 是全局排序的
    //val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 1, 2, 3),2)
    //value.map(x => (x, 1)).sortByKey().saveAsTextFile("datafiles/sortbykey")


    //TODO  repartition和 coalesce(int,boolean) 可以改变分区数:如果boolean为true,可以将原来的分区数变多或变少，当boolean为false,只能将原来的分区数变少
    //    val value: RDD[Int] = context.parallelize(Seq(1, 2, 3, 4, 1, 2, 3), 3)
    //    println(value.coalesce(2, true).getNumPartitions)

    /*
    def repartition(numPartitions: Int)(implicit ord: Ordering[T] = null): RDD[T] = withScope {
    coalesce(numPartitions, shuffle = true)
  }
     */
    // value.repartition(2) // 调用repartition，底层调用的coalesce(numPartitions, shuffle = true)

    //TODO join
    // Option 有两个子类 Some(),None
    val value1: RDD[(String, Int)] = context.parallelize(Seq("a", "b", "c", "d")).map(x => (x, 1))
    val value2: RDD[(String, Int)] = context.parallelize(Seq("b", "c", "d", "e")).map(x => (x, 1))
    println("join")
    val value: RDD[(String, (Int, Int))] = value1.join(value2)
    value.foreach(x => println(x._1 + " " + x._2))
    println("leftjoin")
    // key,左边value，右表value
    val value3: RDD[(String, (Int, Option[Int]))] = value1.leftOuterJoin(value2)
    value3.foreach(x => {
      println(x._1 + " " + x._2._1 + " " + x._2._2.getOrElse(0))
    })
    println("rightjoin")
    val value4: RDD[(String, (Option[Int], Int))] = value1.rightOuterJoin(value2)
    value4.foreach(x => {
      println(x._1 + " " + x._2._1.getOrElse(0) + " " + x._2._2)
    })
    println("fullouterjoin")
    val value5: RDD[(String, (Option[Int], Option[Int]))] = value1.fullOuterJoin(value2)
    value5.foreach(x => println(x._1 + " " + x._2._1.getOrElse(0) + " " + x._2._2.getOrElse(0)))
    //TODO 资源关闭
    context.stop()

    /*
    常用算子：
    map:对rdd的每一行进行映射转换
    flatmap:对rdd的每一行进行映射转换，但是要求用户函数返回一个迭代器
    它会从迭代器中取出各个元素放到结果RDD中
    filter:过滤，要求用户函数返回一个boolean值，为true,数据留下
    mappartitions:对rdd的数据进行映射，要求用户函数一次性接收rdd的一个分区的数据（用迭代器的方式）
    要求用户函数将映射结果也以迭代器的形式返回，一个分区只调用一次
    reduceByKey:按相同key分组，并对组内的value进行聚合
    groupByKey:按key相同分组
    join：两个rdd根据key关联
     */

  }

}
