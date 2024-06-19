package com.bbas.bigdata.chapter7

import scala.collection.mutable.ListBuffer

object ScalaCollectionSeq {
  def main(args: Array[String]): Unit = {
    //TODO 集合 - seq 序列
    // 有序，可重复
    //TODO 构建
    // 1.seq需要指定泛型，seq是一个trait,无法直接构建对象，一半采用半生对象的apply方法
    // 1.seq需要指定泛型，seq是一个trait,无法直接构建对象，所以底层采用的List
    // 构建集合
    val ints: Seq[Int] = Seq.apply(1, 2, 3, 4)
    val ints11: Seq[Int] = Seq(1, 2, 3, 4)
    val ints22: List[Int] = List(1, 2, 3, 4)

    // 序列输出方式
    ints.mkString(".")
    for (elem <- ints) {
      println(elem)
    }
    ints.foreach(x => println(x))
    println(List())

    //TODO 可变集合 Seq
    /*
    1.seq,list => 不可变
    2.listBuffer => 可变
     */

    val ints1: ListBuffer[Int] = ListBuffer(1, 2, 3, 4)
    // 了解可变listBuffer的方法
    // listBuffer与list之间的转换
    ints1.toList
    ints11.toBuffer

  }

}
