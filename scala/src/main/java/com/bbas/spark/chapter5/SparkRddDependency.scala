package com.bbas.spark.chapter5

object SparkRddDependency {
  def main(args: Array[String]): Unit = {
    /**
     * rdd:弹性分布式数据集
     * 1.compute计算函数
     * 2.依赖rdd列表
     * 存储着当前rdd所依赖的一个或多个前序rdd
     * 3.分区列表
     * spark是基于海量数据分布式计算的场景设计的，会把数据分给多个task并行计算，因此，也就需要把数据划分成多个任务片partition
     * 例如:
     *     处理hdfs上的文件，会按文件及偏移量范围划分partition,通常一个hdfs的block块对应一个partition
     *     处理mysql等数据库中的数据室，会按照用户指定的某个字段的取值范围和指定的分区数进行partition划分
     *     引入任务分片机制，让每个task都只负责读取和处理各自所负责的数据分区
     * 4.可选key-value类型RDD的分区器
     * 5。优先分配节点列表（Preferred Locations）
     *
     *
     *
     *
     * SHUFFLE
     * 一旦引入任务分片机制（分区）后，又引入了一个新的问题
     * 每个task都只读取了数据源的一部分。无法得到全局结果
     * 例如：
     * sc.textfile("").flatMap(x=>{x.split(" ")}).map(x=>(x,1)).reduceByKey(_+_)
     * .foreach(println)
     * 将运算逻辑划分成多个stage:每个stage形成独立task任务。上下游衔接借助数据的shuffle。
     * 算完之后结果落到自己stage本地磁盘，落盘的时候把数据进行分区写。（根据下游几个分区，shufflewriter）根据下游partition确定数据如何分区
     * 会再及一个索引文件。记录分区以及偏移量
     * shuffle过程：
     * 每个task输出成本地磁盘文件,输出的时候会根据下游分区数，进行不同分区写数据。同时会带有一个索引文件，记录不同分区和对应的偏移量
     *
     * 第一个stage
     * rdd1=sc.textfile()
     * rdd2=rdd1.map(s=>(s,1))
     * 第二个stage
     * rdd3 = rdd2.groupByKey
     * rdd4 = rdd3.map(x=>(x,1))
     * 第三个stage
     * rdd5 = rdd4.reduceByKey(_+_)
     * rdd5.foreach(print)
     * 当上下游数据需要shuffle,需要划分为不同stage
     *
     *行动算子的重新设计
     * 分布式运算模式下。需要将行动算子功能改造，对整个逻辑链条的分布式任务生成及提交，调度（即job调度）
     * sc.runjob会把用户通过rdd定义的运算连。转化成一系列task计算任务并提交到集群执行
     *
     *
     * mapreduce每一次shuffle都需要把数据写到hdfs
     * spark每次shuffle写到本地磁盘
     *
     *
     *
     *
     */
  }

}
