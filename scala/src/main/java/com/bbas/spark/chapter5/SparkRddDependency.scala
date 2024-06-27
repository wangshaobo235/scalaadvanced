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
     *rdd的血缘与依赖关系
     * dependency概念
     * dependency不仅描述父子血缘，更关键描述了父子RDD的partitions之间的依赖关系
     * dependency是判断是否需要划分stage(即是否需要shuffle)的依据。
     *依赖的实现类
     * shuffledependency 宽依赖
     * narrowdependency 窄依赖
     *
     * 窄依赖的核心特点
     *父rdd的一个分区，一定只会被子rdd的一个分区所依赖。
     * 子rdd的一个分区，可以依赖父RDD的多个分区。
     *
     * 宽依赖
     * 指的是父RDD的一个分区可能会被子rdd的所有分区依赖。
     * 因而父RDD一个分区的数据需要被分割成多份，来形成子RDD的各个分区:
     * 因此宽依赖也意味着从父RDD计算出子RDD的过程中，需要一个洗牌(Shuffle)过程。
     *
     *
     *job调度中的核心概念
     * spark中的job调度：是指spark框架底层根据rdd逻辑链条，划分stage,生成task并提交执行的过程
     *
     * application:创建一个sparkcontext,就生成了一个application
     * job 触发一次行动算子，就产生了一个job
     * 而每一个job中，都包含如下概念:
     * dag : 叫做有向无环图，是一系列rdd转换关系的描述
     * stage：以shuffle为分界线，将DAG转换逻辑 从整体划分成段，每一段就称之为一个stage
     *        本质就是：一个stage中的rdd计算逻辑可以放在一个task执行
     * taskset / task
     * 同一个stage计算的时候会有多个task进行运算，逻辑相同，处理的分区和数据不同。多个task就叫做taskset ,一个taskset包含多个task。
     * 一个stage的并行度由这个stage最后一个rdd的分区数决定。
     *
     *Task/Task Set
     *每一个 stage 中包含的逻辑链条段，需要提交集群去并行执行，每个执行实例就称之为一个task;而这个 stage 所转化出来的这一批task，
     * 称之为task set(一个taskset中所有 task 执行的计算逻辑都相同，不同的只是负责的数据分区不同):
      (task，本质上是一个类，它封装了调用rdd 选代器的代码以及将计算结果输出的代码)
     *Task在spark内部共有2种:shuffleMapTask和resultTask最后一个stage 所产生的task，是resultTask
      其他 stage 所产生的task，都属于 shuffleMapTask
     *task是线程，在executor进程中执行。
     *
     *
     *
     *
     *
     */
  }

}
