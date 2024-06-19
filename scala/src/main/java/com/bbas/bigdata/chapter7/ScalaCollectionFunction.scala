package com.bbas.bigdata.chapter7

object ScalaCollectionFunction {
  def main(args: Array[String]): Unit = {
    val ints: List[Int] = List(1, 2, 3, 4)
    //map方法需要传递一个参数。这个参数的类型为函数类型：Int=>B
    // 这里的Int表示数据集中的每一条数，这里的B表示处理后的结构。但是类型任意
    //功能函数在使用时，都是采用匿名函数，。而且同时简化函数操作
    //todo 当匿名函数只有一个参数，且每一个数据都要进行处理时。就可以在匿名函数中使用下划线
    def test(x:Int):Int={
         x*2
    }
    ints.map(test)
    ints.map(x=>x*2)

    val ints1: List[List[Int]] = List(List(1, 2, 3, 4),List(2, 3, 4, 5))
    val ints2: List[Int] = ints1.flatMap(x=>{
      x
    })

    println(ints1.length + " " + ints2.length)

    // hello,scala,java,python
    val strings: List[String] = List("hello scala", "java python")
    strings.flatMap(x=>{
      x.split(" ")
    }).foreach(x=>println(x))
    // h,e,l,l,o,s,c,a,l,a,j,a,v,a,p,y,t,h,o,n,
    println("*****彻底扁平化*****")
    strings.flatMap(x=>x).foreach(x=>println(x))


    // 过滤保留满足条件数据
    println("*****filter*****")
    ints.filter(x=>x%2==0).foreach(x=>println(x))

    //groupby 方法需要传递一个参数。这个参数的类型为函数类型 Int => k
    // 这里的Int表示数据集中的每一条数据
    // 这里的K表示数据分组的标记
    println("*****groupby*****")
    val intToInts: Map[Int, List[Int]] = ints.groupBy(x => x % 2)
    val intToInts1: Map[String, List[Int]] = ints.groupBy(x => {
      if(x % 2==0 ){
        "偶数"
      }else{
        "奇数"
      }
    })
    intToInts.foreach(x=>{
      println(x._1 +" "+ x._2)
    })




  }

}
