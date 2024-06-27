package com.bbas.spark.chapter5

object SparkRddIterator {
  def main(args: Array[String]): Unit = {
    val ints: List[Int] = List(1, 2, 3, 4, 5)
    val iterator: Iterator[Int] = ints.iterator

    /**
     * scala迭代器算子的lazy特性
     * println("函数1被执行了") -- 不会被打印
     */
    val ints1: Iterator[Int] = iterator.map(x => {
      println("函数1被执行了")
      x + 10
    })

    ints1.foreach(x=>println(x))





  }

}
