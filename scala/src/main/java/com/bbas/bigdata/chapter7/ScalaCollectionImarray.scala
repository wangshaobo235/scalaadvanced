package com.bbas.bigdata.chapter7

import scala.collection.mutable.ArrayBuffer

object ScalaCollectionImarray {
  def main(args: Array[String]): Unit = {
    //TODO 可变数组
    val ints: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    //增删改查
    ints.append(1, 2, 3, 4)
    for (elem <- ints) {
      println(elem)
    }
    ints.remove(1)
    ints.update(2, 10)
    ints.insert(3, 100)
    ints.clear()
    ints.sum
    ints.exists(x => x % 5 == 0)
    ints.mkString("")
    ints.foreach(x => println(x))

    // 将可变变为不可变 将不可变变为可变
    val ints1: Array[Int] = Array(1, 2, 3, 4)
    val ints2: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)

    ints1.toBuffer
    ints2.toArray

  }

}
