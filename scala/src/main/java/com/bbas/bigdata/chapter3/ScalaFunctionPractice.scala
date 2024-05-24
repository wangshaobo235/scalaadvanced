package com.bbas.bigdata.chapter3

/**
 * ClassName: ScalaFunctionPractice
 * Package: com.bbas.bigdata.chapter3
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/24 9:22 
 * @Version 1.0   
 */
object ScalaFunctionPractice {
  def main(args: Array[String]): Unit = {
    def calc(x: Int, f: (Int, Int) => Int, y: Int): Unit = {
      println(f(x, y))
    }

    calc(5, (x, y) => x + y, 5)
    calc(5, (x, y) => x - y, 5)
    calc(5, (x, y) => x * y, 5)
    calc(5, (x, y) => x / y, 5)


    //TODO 函数式编程语言-地狱版
    //1.将函数对象作为变量赋值使用
    //2.将函数对象作为参数使用


    //TODO 函数式编程语言-地狱版
    //1.Scala也可以将函数对象作为返回值结果返回
    def outer() = {
      def inner(): Unit = {

      }

      inner _
    }

    val unit: () => Unit = outer()
    unit()
    outer()() // 等于unit()



    //TODO 函数嵌套返回
    def out(x:Int)={
        def middle(f:(Int,Int)=>Int)={
            def in(y:Int)={
                f(x,y)
            }
            in _
        }
        middle _
    }


    //TODO 函数闭包
    // 闭包：如果一个函数，访问到了它的外部（局部）变量的值，那么这个函数和他所处的环境，称为闭包





    //TODO 练习1：定义一个匿名函数，并将它作为值赋给变量fun。函数有三个参数，类型分别为Int，String，Char，返回值类型为Boolean。
    // 要求调用函数fun(0, “”, ‘0’)得到返回值为false，其它情况均返回true。
    val fun = (para1: Int, para2: String, para3: Char) => if (para1 == 0 && para2 == "" && para3 == '0') false else true
    println(fun(0, "", '1'))


    //TODO 练习2： 定义一个函数func，它接收一个Int类型的参数，返回一个函数（记作f1）。
    // 它返回的函数f1，接收一个String类型的参数，同样返回一个函数（记作f2）。函数f2接
    // 收一个Char类型的参数，返回一个Boolean的值。
    // 要求调用函数func(0) (“”) (‘0’)得到返回值为false，其它情况均返回true。

    def func(a:Int)={
        def f1(b:String)={
            def f2(c:Char):Boolean={
              if ( a== 0 &&  b== "" &&  c== '0') false else true
            }
            f2 _
        }
      f1 _
    }

    println(func(0)("")('0'))



    




  }

}
