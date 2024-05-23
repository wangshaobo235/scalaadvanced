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
object ScalaFunctionNormal {
  def main(args: Array[String]): Unit = {
    //TODO 函数式编程
    // 参数的个数
    // 函数的参数是没有限制个数，但是个数越多，传值越麻烦，所以并不推荐
    //TODO 参数个数的简化 可变参数 类似于java参数的 ...
    // 在某些场景中，函数的参数可能不需要传递，也可能需要传递，甚至可能需要传递多个，不确定具体几个

    def func1(name: String*): Unit = {
      println(name)
    }

    func1()
    func1("zhangsan")
    func1("zhangsan", "lisi", "wangwu")

    // TODO 可变参数的位置
    // 可变参数只能放到参数列表的最后
    // 参数默认值，一般将带有默认值的参数放置到参数列表的最后
    // 一个参数列表不可能有多个可变参数
    def func2(pass: String, name: String*): String = {
      pass + name
    }

    println(func2("123", "test", "test1"))


    // TODO Scala语法中可以给参数设定默认值，一般放到参数列表的最后
    //
    def func3(name: String, pass: String = "000000"): String = {
      s"name=${name},pass=${pass}"
    }

    println(func3("zhangsan"))


    // TODO 如果参数传值，会把默认值覆盖掉
    println(func3("zhangsan"))


    // TODO 函数的带名参数
    println(func3(pass = "111111", name = "test"))

  }


}
