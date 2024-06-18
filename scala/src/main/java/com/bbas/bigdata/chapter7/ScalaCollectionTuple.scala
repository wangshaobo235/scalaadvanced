package com.bbas.bigdata.chapter7

object ScalaCollectionTuple {

  def main(args: Array[String]): Unit = {

    // TODO 集合
    // 集合其实就是数据的容器，可以容纳数据
    // 如果想把无关的数据当做一个整体来使用
    //1.如果数据之间有关系，生成bean对象，对应属性

    //2.如果数据类型含义相同，一般使用集合，List(),Seq[any](),Array[any]

    //3.scala采用特殊的语言将无关数据组合到一起，(),称之为元组
    //元组在scala中属于特殊的集合，采用小括号声明，将数据写到括号内即可。 元组也是集合，也有数据类型，Tuplen[datatype],有个数限制：22个

    var tup: Tuple3[Int, String, Int] = (1, "test", 30)

    // tuple 元素访问
    // 无法使用循环操作，因为数据没有关系，就不能采用相同的循环逻辑
    // 如果想要访问数据，可以采用顺序号 , 最大的顺序号代表元组中数据的个数
    println(tup._1)
    println(tup._2)
    println(tup._3)

    // 如果元组中的元素为2，那么称之为对偶元素。也称之为键值对对象
    val tuple: (String, Int) = "a" -> 1 // 对应元组
    1 -> 2 -> 3 -> 4
    ((1, 2), 3, 4)
    // TODO Map的集合操作有了变化
    val stringToInt: Map[String, Int] = Map(("a", 1), ("b", 2), ("c", 3))

    println(stringToInt)

    for (elem <- stringToInt) {
      println(elem._1 + " " + elem._2)
    }

  }

}
