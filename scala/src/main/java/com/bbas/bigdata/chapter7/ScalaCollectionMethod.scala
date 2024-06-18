package com.bbas.bigdata.chapter7

import scala.collection.mutable.ListBuffer

object ScalaCollectionMethod {
  def main(args: Array[String]): Unit = {
    //TODO 集合常用方法 通用方法
    val ints: List[Int] = List(1, 2, 3, 4)
    //mkstring
    ints.length
    ints.size
    ints.isEmpty
    ints.contains("2")
    ints.reverse
    ints.distinct // 等于 ints.toSet
    ints.mkString(",")
    ints.foreach(println(_))
    // 获取第一个数据
    ints.head
    // 获取除了第一个以外的剩余数据
    ints.tail // 2,3,4
    ints.tails
    //获取最后一个
    ints.last
    //除了最后一个的剩余数据
    ints.init
    //拿取三条数据
    val ints1: List[Int] = ints.take(3)
    ints.takeRight(2)
    // 删除数据
    ints1.drop(1)

    def test(a:Int):Int={
      a*2
    }
    //每个元素乘以2 笨方法
    def transform(list:List[Int],f:Int=>Int):List[Int]={
      val buffer: ListBuffer[Int] = ListBuffer[Int]()
      ints.foreach(x=>{
        buffer.append(f(x))
      })
      buffer.toList
    }

    // 调用集合的map
    val ints2: List[Int] = ints.map(x => x * 2)
    println(ints2.mkString(","))







  }

}
