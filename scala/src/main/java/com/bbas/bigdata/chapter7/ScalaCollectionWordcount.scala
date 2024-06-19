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


    //TODO 使用功能函数事项 wordcount

    println("第二版wordcount")
    val tuples: List[(String, Int)] = List(("hello scala spark java", 4), ("hello python php", 3), ("scala spark r java hello", 2))
    val tuples1: List[(String, Int)] = tuples.flatMap(x => {
      val strings: Array[String] = x._1.trim.split(" ")
      strings.map(y => ((y, x._2)))
    })

    val stringToTuples: Map[String, List[(String, Int)]] = tuples1.groupBy(x => x._1)
    println(stringToTuples.map(x => {
      (x._1, x._2.map(x => x._2).sum)
    }))



  }

}
