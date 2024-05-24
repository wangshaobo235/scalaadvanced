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
object ScalaFunctionPracticeThree {
  def main(args: Array[String]): Unit = {
    // TODO 将简化版本的函数式编程代码恢复成完成代码


    // test(10,20,_+_)
    test(10,20,(x:Int,y:Int)=>{x+y})
    def test(x: Int, y: Int, op: (Int, Int) => Int): Unit = {
      println(op(x, y))
    }
      def calc(x:Int,y:Int):Int={
        x+y
      }

    test(10,20,calc)

    // test1(_.substring(0,1))


    def test1(op:String=>String)={
        op("zhangsan")
    }

    def sub(x:String)={
      x.substring(0,1)
    }

    println(test1(sub))

    // test2(_*2)

    def test2(op:Int=>Int)={
        op(2)
    }

    def test3(x:Int)={
      x*2
    }

    println(test2(test3))

  }

}
