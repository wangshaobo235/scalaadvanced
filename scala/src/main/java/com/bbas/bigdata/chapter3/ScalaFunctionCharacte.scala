package com.bbas.bigdata.chapter3

/**
 * ClassName: ScalaFunctionCharacte
 * Package: com.bbas.bigdata.chapter3
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/23 14:56 
 * @Version 1.0   
 */
object ScalaFunctionCharacte {
  def main(args: Array[String]): Unit = {
    //TODO 函数式编程语言-地域版
    // 函数其实就是对象
    //1.对象应该有类型
    //2.对象应该可以赋值给其他人使用
    def test(): Unit = {

    }

    // 将函数对象test赋值给testCopy
    //  此时f可以通过编译器自动推断类型，函数对象的类型称之为函数类型
    //  Function0[Unit]
    //  这里类型中的0表示函数参数列表中参数的个数
    //  中括号中的Unit表示函数没有返回值
    val testCopy: Function0[Unit] = test _

    def test1(s: Int): String = {
      ""
    }

    // Function1[Int,String]
    // 这里类型中的1表示函数参数列表中参数的个数
    // 中括号中的Int表示输入的参数类型，String表示函数的输出类型
    val testCopy1: Function1[Int, String] = test1 _

    //TODO[函数对象]的参数最多只能有22个
    //    [函数]的参数个数没有限制


    //TODO 为了使用方便，函数类型可以使用另一中声明方式
    // 这里的函数类型为： Int(参数列表的参数类型)=>String(返回值类型)
    val testCopy2: Int => String = test1 _

    def test3(name: String, age: Int): Unit = {

    }

    //(String,Int)=>Unit
    val testCopy3: (String, Int) => Unit = test3 _



    // TODO 函数式编程语言 - 地狱版
    // 将函数对象赋值给一个变量，那么这个变量其实就是一个函数
    // 既然这个变量就是函数，所以这个变量可以传参后执行的

    def test4(): Unit = {
      println("test")
    }

    val testCopy4: () => Unit = test4 _
    testCopy4()

    //TODO 将函数对象作为参数使用

    println("将函数对象作为参数使用")

    def fun(): Unit = {
      println("test")
    }

    def funtest(f: () => Unit): Unit = {
      f()
    }

    funtest(fun _)
    println(funtest(fun _))

    //TODO 函数式编程语言 - 地狱版
    //
    //

    def funcSum(a: Int, b: Int): Int = {
      a + b
    }

    def funcTest(a: Int, b: Int, op: (Int, Int) => Int): Unit = {
      println(op(a, b))
    }

    // 将函数传递给另一个函数
    funcTest(10, 20, funcSum)
    //TODO 匿名函数
    // 传递匿名函数至简原则：
    // （1）参数的类型可以省略，会根据形参进行自动的推导
    // （2）类型省略之后，发现只有一个参数，则圆括号可以省略；其他情况：没有参数和参
    // 数超过1的永远不能省略圆括号。
    // （3）匿名函数如果只有一行，则大括号也可以省略
    // （4）如果参数只出现一次，则参数省略且后面参数可以用_代替
    funcTest(10, 20,(a: Int, b: Int) => {
      a + b
    })
    funcTest(10, 20, _ + _)


  }

}
