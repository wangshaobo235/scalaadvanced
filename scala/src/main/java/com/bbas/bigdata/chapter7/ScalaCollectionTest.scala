package com.bbas.bigdata.chapter7

object ScalaCollectionTest {
  def main(args: Array[String]): Unit = {
      def firstUpper(para:String*):Seq[String]={
          // para.substring(0,1).toUpperCase()+para.substring(1)
        para
      }

    def firstUpper1(para: String): String = {
      val maybeString: Option[String] = Option(para)
      if(maybeString.isEmpty) {
        maybeString.getOrElse("")
      }else{
        val value: String = maybeString.get
        if(value.trim.length==0){
           ""
        }else{
          para.substring(0,1).toUpperCase()+para.substring(1)
        }
      }

    }

    println(firstUpper(""))
    println(firstUpper(null))
    println(firstUpper("lisi"))


  }

}
