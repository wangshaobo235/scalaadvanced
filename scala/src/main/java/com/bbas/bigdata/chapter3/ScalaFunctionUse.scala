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
object ScalaFunctionUse {
  def main(args: Array[String]): Unit = {
    //TODO 函数式编程语言
    /*
      函数的应用，说的简单点就是函数的声明与使用
      函数其实就是功能的封装，意味着功能已经按照特定规则封装好了，重点在如何调用
      函数式编程侧重点：函数名，输入参数，返回结果
     */

    /* TODO 函数声明
    1）函数定义
      （1）函数1：无参，无返回值
      （2）函数2：无参，有返回值
      （3）函数3：有参，无返回值
      （4）函数4：有参，有返回值
      （5）函数5：多参，无返回值
      （6）函数6：多参，有返回值
     */
    def func1(): Unit = {
      println("func1...")
    }
    // 调用函数的时候需要指定函数的名称及传递的参数 如果参数列表中没有声明任何的参数，那么调用时参数列表的 小括号可以省略
    func1()
    func1

    def func2(): String = {
      "test"
    }

    val func: String = func2
    println(func)

    // 如果参数只有一个时，也不能省略小括号，方法可以省略作为运算符使用
    def func3(s:String):Unit={
      println(s)
    }

    func3("有参无返回值")
    println(func3("有参无返回值"))

    def func4(s:String):String={
        s
    }

    println(func4("有参有返回值"))


    def func5(name:String,age:Int):Unit={
        println(s"name=${name} age=${age}")
    }

    func5("张三",10)


    def func6(name:String,age:Int):String={
      s"name=${name} age=${age}"
    }

    println(func6("李四", 20))


  }


}
