package com.bbas.bigdata.chapter7

import scala.collection.mutable

object ScalaCollectionSet {
  def main(args: Array[String]): Unit = {
      //TODO set - 集合
      /*
      scala 集合 描述了 无序。数据不可重复集合

       */
    //TODO SET 是一个trait,不能直接构建对象，而且默认为不可变集合
    // 一半情况下，集合都是采用伴生对象apply方法

    val ints: Set[Int] = Set(1, 1, 1, 2, 3, 4)

    // 了解 不可变 set 的方法


    // TODO 可变的set集合，需要根据包名进行区分
    val ints1: mutable.Set[Int] = mutable.Set(1, 2, 3, 1)
    // 增删改查
    ints1.add(1)
    ints1.remove(1)
    ints1.update(1,false) // set没有索引，false 删除 第一个参数
    for (elem <- ints1) {
      println(elem)
    }





   }

}
