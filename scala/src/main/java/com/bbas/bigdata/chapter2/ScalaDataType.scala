package com.bbas.bigdata.chapter2

/**
 * ClassName: ScalaDataType
 * Package: com.bbas.bigdata.chapter2
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/17 16:46 
 * @Version 1.0   
 */
object ScalaDataType {
  def main(args: Array[String]): Unit = {
    //TODO java中数据类型分为基本数据类型、引用数据类型
    //TODO Scala语言是一个完全面向对象的语言，所以没有基本数据类型的概念
    //TODO Scala中数据类型分为两大类：任意值类型（AnyVal）、任意引用类型（AnyRef），不管是值类型还是引用类型都是对象
    //任意的值类型 anyval： Byte Short Int Long Float Double Boolean Char Unit(方法没有返回值，只有一个对象，() ) stringops 跟字符串相关的一个类型
    //任意的引用类型 anyref：所有的java类型，scala集合，scala类型 都是引用类型
    //引用数据类型为空时，一般会赋值为null,但是null本身也有数据类型，为Null类型
    // Nothing，是所有数据类型的子类，主要用在一个函数没有明确返回值时使用，因为这样我们可以把抛出的返回值，返回给任何的变量或者函数
    // 一般应用于异常返回处理
    // Scala中一切数据都是对象，都是Any的子类
    println(test1) // 返回值 ()

    // DataType transform 数据类型转换
    val para1:Int=10
    val para2:String="abc"
    val para3:AnyVal=para1
    val para4:AnyRef=para2
    var para5:Any=para1
     para5=para2





  }

  def test1():Unit={

  }

}
