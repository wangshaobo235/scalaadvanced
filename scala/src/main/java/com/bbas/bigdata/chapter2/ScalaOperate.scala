package com.bbas.bigdata.chapter2

import scala.io.StdIn

/**
 * ClassName: ScalaOperate
 * Package: com.bbas.bigdata.chapter2
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/21 16:27 
 * @Version 1.0   
 */
object ScalaOperate {
  def main(args: Array[String]): Unit = {
    val age = 10
    //TODO ：Scala中if else表达式其实是有返回值的，具体返回值取决于满足条件的代码体的最后一行内容
    // Scala中返回值类型不一致，取它们共同的祖先类型
    // 如果大括号{}内的逻辑代码只有一行，大括号可以省略。如果省略大括号，if只对最近
    // 的一行逻辑代码起作用
    if (age < 18) {
      println("童年")
    }else if(age >18 && age<30){
      println("中年")
    }else{
      println("老年")
    }
    var result = if (age < 18)
      "童年"
    else {
      10
      "中年"
    }





  }

}
