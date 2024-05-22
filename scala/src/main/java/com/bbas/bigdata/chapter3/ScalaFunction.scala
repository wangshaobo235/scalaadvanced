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
object ScalaFunction {
  def main(args: Array[String]): Unit = {
    //TODO 函数式编程
    /*
       1.scala中的函数不是数学中的函数，这里的函数表示功能的封装.java中功能的封装一般称之为方法。
       2.java中的方法在scala中就是函数。声明在类中的函数叫做类的方法。函数只在当前作用域中有效，但是方法需要受到类的约束。
       3.因为函数其实就是功能的封装，就意味着功能的名称不能重复，没有重写，重载的概念。
       4.因为方法属于类，那么就可能存在方法的重载与重写。
     */
    // val s = "zhangsan"
    // println(s.substring(0, 1).toUpperCase + s.substring(1))

    //TODO 函数的声明与使用
    /*声明 =>
     def 方法名(参数列表):返回值类型={
       函数体
     }
     使用 => 方法名()
     */

    def printInfo(): Unit = {
      println("打印信息...")
    }

    printInfo()

  }


}
