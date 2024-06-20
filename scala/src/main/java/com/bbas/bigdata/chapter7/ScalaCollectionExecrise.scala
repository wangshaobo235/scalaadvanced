package com.bbas.bigdata.chapter7

object ScalaCollectionExecrise {
  def main(args: Array[String]): Unit = {

    //数据
    val srcDatas = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    val stringToTuples: Map[String, List[(String, String,String)]] = srcDatas.groupBy(x => x._2)
    val stringToTuples1: Map[String, List[(String, Int)]] = stringToTuples.mapValues(x => {
      val strings: List[String] = x.map(y => {
        y._3
      })
      val stringToStrings: Map[String, List[String]] = strings.groupBy(x => x)
      val stringToInt: Map[String, Int] = stringToStrings.mapValues(x => x.size)
      stringToInt.toList.sortBy(x => {
        x._2
      })(Ordering.Int.reverse)

    })

    println(stringToTuples1)



  }

}
