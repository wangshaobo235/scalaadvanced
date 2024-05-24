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
object ScalaFunctionPracticeTwo {
  def main(args: Array[String]): Unit = {
      def func(f:String=>Unit)={
          f("test")
      }

      def test(x:String)={
        println("name "+ x)
      }
      func( x=>println("name "+x) )
      // 参数按照顺序只使用一次，可以使用下划线代替，但是不能嵌套使用
      // func( println("name "+_) ) 报错
      func(println(_)) // 至简原则

      func(println) //



      func(println)
  }

}
