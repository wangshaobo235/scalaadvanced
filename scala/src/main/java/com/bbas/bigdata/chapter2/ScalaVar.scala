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
object ScalaVar {
  def main(args: Array[String]): Unit = {
    //TODO 变量
    // scala中声明变量 变量必须显示初始化
    // var/val 变量名称:变量类型 = 变量值
    // java 变量类型 变量名称 = 变量值
    val name: String = "Scala"
    // name = "java" val：不可变变量 修饰的变量不可被修改
    var age = 10 // var ：可变变量  修饰的变量，值可以被修改，但是类型不能被修改
    age = 30
    printf("%s",name)
    print(s"${name}")
    var score: Double = 20.0

    // scala 开发原则： 至简原则，能省则省，编译器帮助我们进行补充

  }
}
