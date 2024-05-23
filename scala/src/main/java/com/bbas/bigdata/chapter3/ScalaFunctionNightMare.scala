package com.bbas.bigdata.chapter3

/**
 * ClassName: ScalaFunctionNightMare
 * Package: com.bbas.bigdata.chapter3
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/23 10:46 
 * @Version 1.0   
 */
object ScalaFunctionNightMare {

  def main(args: Array[String]): Unit = {

    //TODO 函数式编程-至简原则
    // 1.如果函数体里的逻辑代码需要返回，可以省略reurn关键字，满足条件的最后一行代码作为返回值
    // 2.如果函数的逻辑代码只有一行，那么可以将花括号省略
    // 3.如果可以通过返回值推断出返回值类型，那么返回值类型可以省略
    // 4.如果函数的参数列表没有声明参数，那么小括号可以省略  (函数在调用的时候不可以加小括号)
    // 5.如果逻辑代码中有return返回，但是函数声明为Unit,此时return不起作用, 如果想要省略Unit,又不希望发生错误，可以将等号同时省略
    // 6.如果函数名称不重要的时候，def和函数名也可以省略，称之为匿名函数
    // def和函数名要省略
    // 返回值类型也需要省略，由返回值自动推断
    // 等号需要增加大于号表示关联

    /*
    def test():String={
     return "zhangsan"
    }
     */


    def test(): String = {
      "张三"
    }

    def test1(): String = "张三"

    def test2() = "张三"

    def test3 = "张三"

    def test5(): Unit = {
      return "test"
    }

    // 肯定不返回值，等号可以省略，一般是和Unit同时省略
    def test6() {
      return "test"
    }

      //TODO Scala语言是完全面向对象的语言，所以万物皆对象
      // Scala语言是完全面向函数式编程语言，所以万物皆函数
      // 如果不想让函数执行，只是想访问这个函数本身，可以采取特殊符号（下划线 ）进行转换 函数名 _
      def funcTest():Unit = {
          // println("test")
      }
      println(funcTest)
      println(funcTest _)


  }

}
