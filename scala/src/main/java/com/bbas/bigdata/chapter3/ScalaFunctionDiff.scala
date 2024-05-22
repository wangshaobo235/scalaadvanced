package com.bbas.bigdata.chapter3

/**
 * ClassName: ScalaFunction
 * Package: com.bbas.bigdata.chapter3
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/22 10:39 
 * @Version 1.0   
 */
object ScalaFunctionDiff {
  def main(args: Array[String]): Unit = {
    //TODO 函数式编程
    /*
       函数 & 方法
       1.函数可以声明在任意的位置，方法只能声明在类中
       2.如果函数名称与方法名称相同，默认情况下，会调用函数。
         如果没有函数，会调用方法。
       3.方法和对象相关，函数和对象无关。
     */

    //TODO 函数式编程语言
    /*函数的本质就是java中方法
      scala源码中，方法就是函数，编译后的字节码中，函数就是方法
      函数编译成方法时，增加了修饰符： private static final

     */

    def printInfo(): Unit = {
      println("调用函数...")
    }

    printInfo() // 调用函数
    ScalaFunctionDiff.printInfo() // 调用方法


  }

  def printInfo(): Unit = {
    println("调用方法...")
  }

}
