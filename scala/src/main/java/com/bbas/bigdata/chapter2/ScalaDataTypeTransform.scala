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
object ScalaDataTypeTransform {
  def main(args: Array[String]): Unit = {
    //TODO java中数据类型分为基本数据类型、引用数据类型
    // 隐式转换
    // Java：
    //==比较两个变量本身的值，即两个对象在内存中的首地址；
    // equals比较字符串中所包含的内容是否相同。
    // Scala：==更加类似于Java中的equals，参照jd工具
    //如果想要比较对象的内存地址，需要曹采用eq方法
    //

    val a: String = "abc"
    val b: String = "abc"
    val c: String = new String("abc")
    val d: String = new String("bcd")
    println(a == b) // true
    println(a.equals(b)) // true
    println(c == d) //false
    println(c.equals(d)) //false
    println(a.equals(c)) // true
    println(a eq c) // false



  }

}
