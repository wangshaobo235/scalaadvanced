package com.bbas.bigdata.chapter1

/**
 * package : 包，等同于 java中的package
 * object : 声明对象（单例对象）
 * scala是完全面向对象的语言，但是java中静态语法不是针对面向对象的。
 * scala中没有静态语法。采用object来模拟java静态语法，可以通过对象名称来实现静态操作。
 * 如果使用object来声明一个对象，编译之后会生成class文件。
 * ScalaHelloWorld.class
 * ScalaHelloWorld$.class
 * ScalaHelloWorld:单例对象名称，类名
 *
 * def main(args:Array[String]):Unit = {
 * 方法体；
 * }
 * def:方法声明的关键字
 * main:程序主入口
 * ():小括号表示参数，参数可有可无。多个参数用","分隔
 * args:Array[String]:方法参数
 * java : String[] args java语言是强类型语言，编译时期需要明确类型
 * scala: args:String[]
 * scala基于java,也是强类型语言，作者认为开发时参数更重要，所以名称在前，类型在后，为了使用方便，将名称跟类型用":"分隔开
 * Unit:表示方法返回值类型，等同于void:没有返回值
 * "=" ：赋值 将代码块逻辑赋值给一个方法名
 * {} : 方法的主体内容
 * 代码没有分号结尾，可以省略分号
 *
 * ClassName: ScalaHelloWorld
 * Package: com.bbas.bigdata.chapter1
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/16 10:11 
 * @Version 1.0   
 */
object ScalaHelloWorld {
  def main(args: Array[String]): Unit = {
    System.out.println("hello world java ");
    println("hello world scala ")
  }
}
