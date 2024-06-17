package com.bbas.bigdata.chapter7

object ScalaCollectionArray {
  def main(args: Array[String]): Unit = {
    //TODO 集合-数组
    // java 中数组不算集合，java中集合都有自己的类型。 String [] array = new String[4];
    // scala是一个完全面向对象的语言，所有万物皆对象。数据也是对象，也有自己的类型。array
    // scala的Array本质上就是java中的[]
    // scala中的中括号表示泛型

    //TODO 数组的基本操作
    // 第一种方式：创建
    val strings: Array[String] = new Array[String](4)
    // 第二种方式：创建 调用apply
    /*
    def apply(x: Int, xs: Int*): Array[Int] = {
    val array = new Array[Int](xs.length + 1)
    array(0) = x
    var i = 1
    for (x <- xs.iterator) { array(i) = x; i += 1 }
    array
  }
     */
    val ints: Array[Int] = Array.apply(1, 2, 3, 4)
    // 增删改查
    //array没有添加和删除的功能，只有修改数据：通过索引的方式修改数据 数组名+小括号
    // 修改
    strings(0) = "test"
    strings(1) = "test1"
    // 查询 直接把数组中元素以，隔开
    strings.mkString(",")
    // 基本for循环
    for (elem <- strings) {
      println(elem)
    }
    // 采用面向对象的方式实现循环操作：foreach :将集合中的每一条数据进行处理 处理逻辑不是固定的，由开发人员自行决定。
    // 方法的参数只有一个 string=>Unit
    println(strings.foreach(x => println(x))) // [Ljava.lang.String;@31610302
    println(strings.foreach(println(_)))

    //TODO array其实是不可变数组,添加数据不会改变自身，会产生新的数组
    // 一般情况下，调用集合对象的特殊符号的方法时，都会采用运算符的方式来使用
    // 如果运算符采用冒号结尾，那么运算规则为从右向左计算
    val ints1: Array[Int] = ints.+:(5) //向头添加
    val ints2: Array[Int] = ints.:+(6) //向尾添加

    for (elem <- ints1) {
      println(elem)
    }
    println("*** ***")
    for (elem <- ints2) {
      println(elem)
    }

    // 多维数组
    val array: Array[Array[Int]] = Array.ofDim[Int](3, 2)
    array.foreach(x => x.mkString(","))

    // 创建指定范围的数组
    val ints3: Array[Int] = Array.range(0, 10)
  }

}
