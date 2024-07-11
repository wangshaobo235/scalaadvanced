package com.bbas.bigdata.chapter7

import scala.collection.mutable

object ScalaCollectionMap {
  def main(args: Array[String]): Unit = {
      //TODO map集合中存储的是K-V键值对
      // 这个数据是无序的，而却k不能是重复。v可以重复
      // 可以通过map中的key关联到value
      // Map是一个trait,一般情况下，采用伴生对象的apply方法进行构建，默认为不可变map集合
    //TODO 构建
    // scala中kv键值对可以采用特殊的方式构建
    val kv = "a"->1
    val stringToInt: Map[String, Int] = Map(kv, "b" -> 2, "c" -> 3,"a"->5)
    //TODO 增删改查
    // 不可变Map集合的数据基本操作还是特殊符号
    for (elem <- stringToInt) {

    }


    //TODO 构建可变Map
    val stringToInt1: mutable.Map[String, Int] = mutable.Map(kv, "b" -> 2, "c" -> 3, "a" -> 5)
    stringToInt1.put("b",4)
    stringToInt1.put("d",5)

    //  stringToInt1.update()

    stringToInt1.remove("")

    stringToInt1.clear()

    // 取数据，根据key获取value 获取的结果类型为option类型，表示选项类型。这个类型只有2个对象可以选择
    // some:有值 如果有值。那么可以获取真实结果  None :无值  无需获取结果，如果获取，会发生错误
    val maybeInt: Option[Int] = stringToInt1.get("a")
    if(maybeInt.isEmpty){
       ""
    }else{
      maybeInt.get
    }

    maybeInt.getOrElse(0) //有值取值，无值赋予0

    // map集合为了操作方便。也提供了类似于getorelse的方法
    stringToInt1.getOrElse("a",0)







  }
}
