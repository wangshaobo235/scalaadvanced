package com.bbas.bigdata.chapter2

/**
 * ClassName: ScalaVar
 * Package: com.bbas.bigdata.chapter2
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/17 9:26 
 * @Version 1.0   
 */
object ScalaName {
  def main(args: Array[String]): Unit = {
    //TODO 命名规则
    /*
    类名，方法名，变量名，对象名，参数名
    java中标识符规则
    字母，数字，下划线，美元符号
    数字不能开头
    长度没有限制
    区分大小写
    不能是关键字或者保留字

    Scala对各种变量、方法、函数等命名时使用的字符序列称为标识符。即：凡是自己可以起名字的地方都叫标识符。
    1）命名规则
    Scala中的标识符声明，基本和Java是一致的，但是细节上会有所变化，有以下三种规
    则：
      （1）以字母或者下划线开头，后接字母、数字、下划线
      （2）以操作符开头，且只包含操作符（+ - * / # !等）
      （3）用反引号`....`包括的任意字符串，即使是Scala关键字（39个）也可以

     */
    val + = "test"
    val - = "test"
    val +*-/#! = "test"
    //val +*-/#!1 = "test"  以操作符开头，且只包含操作符
  }
}
