package com.bbas.bigdata.chapter8

object ScalaMatch {
  def main(args: Array[String]): Unit = {
    //TODO 模式匹配
    // 这里的匹配其实就是匹配数据的规则
    // 这里的模式其实就是特定写法的规则
    // 代码执行完毕。匹配奇数。不会有穿透现象。scala语言中没有break关键字
    // 如果多个规则匹配不成功。可以使用下划线表示任意值。类似于default
    // java的switch的穿透现象仅仅是个现象。不是错误
    // 恰恰这个现象可能会导致代码的歧义
    // 如果代码逻辑只有一行。那么可以省略大括号，但是需要明确执行完毕后。自动跳出
    // java的switch语法，case 孤雁剪子和default关键字存在执行先后顺序。
    // scala中没有default关键字。所以如果将下划线分支放置在最前边，那么会优先执行
    // scala中的模式匹配的只要作用就是按照指定的规则对数据进行匹配。
    // 如果数据没有匹配任何的规则。就会发生错误
    //

    val age = 30
    age match {
      case 10 => {
        println("年龄=10")
      }
      case 20 => {
        println("年龄=20")
      }
      //case _ => {
      //  println("年龄其他")
      // }
    }


  }

}
