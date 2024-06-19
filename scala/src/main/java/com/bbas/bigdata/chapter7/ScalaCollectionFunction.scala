package com.bbas.bigdata.chapter7

object ScalaCollectionFunction {
  def main(args: Array[String]): Unit = {
    val ints: List[Int] = List(1, 2, 3, 4)
    //map方法需要传递一个参数。这个参数的类型为函数类型：Int=>B
    // 这里的Int表示数据集中的每一条数，这里的B表示处理后的结构。但是类型任意
    //功能函数在使用时，都是采用匿名函数，。而且同时简化函数操作
    //todo 当匿名函数只有一个参数，且每一个数据都要进行处理时。就可以在匿名函数中使用下划线
    def test(x:Int):Int={
         x*2
    }
    ints.map(test)
    ints.map(x=>x*2)

    val ints1: List[List[Int]] = List(List(1, 2, 3, 4),List(2, 3, 4, 5))
    val ints2: List[Int] = ints1.flatMap(x=>{
      x
    })

    println(ints1.length + " " + ints2.length)

    // hello,scala,java,python
    val strings: List[String] = List("hello scala", "java python")
    strings.flatMap(x=>{
      x.split(" ")
    }).foreach(x=>println(x))
    // h,e,l,l,o,s,c,a,l,a,j,a,v,a,p,y,t,h,o,n,
    println("*****彻底扁平化*****")
    strings.flatMap(x=>x).foreach(x=>println(x))


    // 过滤保留满足条件数据
    println("*****filter*****")
    ints.filter(x=>x%2==0).foreach(x=>println(x))

    //groupby 方法需要传递一个参数。这个参数的类型为函数类型 Int => k
    // 这里的Int表示数据集中的每一条数据
    // 这里的K表示数据分组的标记
    println("*****groupby*****")
    val intToInts: Map[Int, List[Int]] = ints.groupBy(x => x % 2)
    val intToInts1: Map[String, List[Int]] = ints.groupBy(x => {
      if(x % 2==0 ){
        "偶数"
      }else{
        "奇数"
      }
    })
    intToInts.foreach(x=>{
      println(x._1 +" "+ x._2)
    })

    //mapValues 方法需要传递一个参数。这个参数的类型为函数类型 Int => k
    val stringToInt: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val stringToInt1: Map[String, Int] = stringToInt.mapValues(x => x * 2)
    stringToInt1.foreach(println(_))
    println("***** *****")
    stringToInt.map(x=>{
      (x._1,x._2*2)
    }).foreach(println(_))


    //sortBy 排序 表示将数据集中得每一条数据按照指定的规则进行排序
    //sortBy方法可以传递一个参数。参数类型为函数类型：Int=>B
    //这里的Int就是数据集中得每一条数据
    //这里的B其实就是排序的标记
    //sortBy按照标记，对数据进行排序
    //sortBy默认情况下是升序排序
    val ints3: List[Int] = List(1, 3, 2, 4)
    val ints4: List[Int] = ints3.sortBy(x => x)
    println(ints4)
    //TODO 默认情况下 sortby是升序排序。如果想要降序。需要传递第二个参数列表
    // sortBY方法存在函数科利华。可以传递多个列表
    ints3.sortBy(x => x.toString)(Ordering.String.reverse)
    val user = new user()
    val user1 = new user()
    val user2 = new user()
    val user3 = new user()

    user.age = 10
    user1.age = 20
    user2.age = 30
    user3.age = 30

    user.money = 100
    user1.money = 200
    user2.money = 300
    user3.money = 400

    val users: List[user] = List(user, user1, user2,user3)

    val users1: List[user] = users.sortBy(x => x.age)(Ordering.Int.reverse)
    println(users1)


    //sortWith
    // sortWith 方法需要传递一个参数，参数的类型为函数类型 (user,user)=>boolean
    // 这里的user就是叽盒子用于比较的两个user对象
    // 这里的boolean表示预计的排序结果判断值 true 预想的结果 false 不是预想的结果
    // users.sortBy(x => x.age).sortBy(x=>x.money)
    println(users.sortWith((x, y) => {
      if (x.age < y.age) {
        true
      } else if (x.age == y.age) {
        x.money > y.money
      } else {
        false
      }
    }))

    // Tuple 排序
    // 默认情况下。sortby排序为升序排序。如果想要降序，那么需要传递第二个列表
    users.sortBy(x=>{
      (x.age,x.money)
    })(Ordering.Tuple2(Ordering.Int,Ordering.Int.reverse))










  }

  class user{
    var age:Int=_
    var money:Int=_


    override def toString = s"user($age, $money)"
  }

}
