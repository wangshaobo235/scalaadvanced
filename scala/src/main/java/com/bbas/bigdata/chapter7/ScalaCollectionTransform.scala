package com.bbas.bigdata.chapter7

object ScalaCollectionTransform {
  def main(args: Array[String]): Unit = {
    //TODO 集合之间转换 list转换成array,set
    val ints: List[Int] = List(1, 2, 3, 4)
    ints.toArray
    ints.toSet
    //TODO 集合之间转换 set转换成array,list
    val ints1: Set[Int] = Set(1, 12, 3, 4, 5)
    ints1.toArray
    ints1.toSet

    //TODO 集合之间转换 map转换成array,set
    val stringToInt: Map[String, Int] = Map(("a", 1), ("b", 2), ("c", 3))
    val array: Array[(String, Int)] = stringToInt.toArray
    val list: List[(String, Int)] = stringToInt.toList
    val set: Set[(String, Int)] = stringToInt.toSet




   }

}
