package com.bbas.bigdata.chapter2

import scala.io.{BufferedSource, Source, StdIn}

/**
 * ClassName: ScalaStringPrint
 * Package: com.bbas.bigdata.chapter2
 * Description: 
 *
 * @Author 王少波
 * @Create 2024/5/17 14:09 
 * @Version 1.0
 */
object ScalaStringPrint {
  def main(args: Array[String]): Unit = {
      val name = "测试"
      val age = 20
      val text=
        s"""
          |1
          |2
          |3
          |${age+4}
          |""".stripMargin
    printf("%s %d\n",name,age) // 传值
    print(s"${name} ${age+2}\n") //插值，加{}参数可以在里边就行逻辑处理，注意：不能拼接json字符串
    print(text)

    // | 表示顶格符 主要用于sql和json
    val text1=
      s"""
        |{"name":"$name","age":$age}
        |""".stripMargin
    print(text1)
    // 从控制台获取数据
    val str: String = StdIn.readLine()
    // 从文件中获取数据
    val source: BufferedSource = Source.fromFile("d://")
    val strings: Iterator[String] = source.getLines()
    while (strings.hasNext){
          strings.next()
    }
    source.close()
  }

}
