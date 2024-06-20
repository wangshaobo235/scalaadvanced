package com.bbas.bigdata.chapter7

import scala.io.{BufferedSource, Source}

object ScalaCollectionWordcount {
  def main(args: Array[String]): Unit = {
    val source: BufferedSource = Source.fromFile("datafiles/wordcount.txt")
    val list: List[String] = source.getLines().toList
    val stringToInt: Map[String, Int] = list.flatMap(x => {
      x.trim.split(" ")
    }).map(x => (x, 1))
      .groupBy(x => x._1)
      .mapValues(x => x.length)

    stringToInt.toList.sortBy(x=>x._2)(Ordering.Int.reverse).foreach(x=>println(x))
    println("新版排序")

    stringToInt.toList.
      sortBy(x=>{
        (x._1,x._2)
      })(Ordering.Tuple2(Ordering.String,Ordering.Int.reverse))
      .foreach(x=>println(x))

    source.close()


    println("第二版wordcount")
    //TODO 使用功能函数事项 wordcount

    val tuples: List[(String, Int)] = List(("hello scala spark java", 4), ("hello python php", 3), ("scala spark r java hello", 2))
    val tuples1: List[(String, Int)] = tuples.flatMap(x => {
      val strings: Array[String] = x._1.trim.split(" ")
      strings.map(y => ((y, x._2)))
    })

    val stringToTuples: Map[String, List[(String, Int)]] = tuples1.groupBy(x => x._1)
    println(stringToTuples.map(x => {
      (x._1, x._2.map(x => x._2).sum)
    }))


    val ints: List[Int] = List(1, 2, 3, 4)
    // reduce方法可以实现自定义的聚合计算
    // reduce需要方法需要传递一个参数，参数类型为函数类型(a,a)=>a
    // 这里的a表示两两计算时。数据类型保持一致，且计算结果的类型保持一致
    // reduce方法的返回值类型也是a
    println(ints.reduce((x, y) => x + y))

  }

}
