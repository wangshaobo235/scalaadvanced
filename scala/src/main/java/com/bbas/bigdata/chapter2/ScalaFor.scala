package com.bbas.bigdata.chapter2

/**
 * ClassName: ScalaOperate
 * Package: com.bbas.bigdata.chapter2
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/21 16:27 
 * @Version 1.0   
 */
object ScalaFor {
  def main(args: Array[String]): Unit = {
    val age = 10
    //TODO ：Scala中if else表达式其实是有返回值的，具体返回值取决于满足条件的代码体的最后一行内容
    for(i<- 1 to 9){
      for(j<- 1 to i){
        print(s"${i}*${j}=${i*j} ")
      }
      println()
    }
  }

}
