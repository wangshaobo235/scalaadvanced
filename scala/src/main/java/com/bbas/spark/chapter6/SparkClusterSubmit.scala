package com.bbas.spark.chapter6

object SparkClusterSubmit {
  def main(args: Array[String]): Unit = {
    /**
     * spark程序分布式运行的模式有很多种：
     * standalone模式，分为两种:
     *       client  可以引用本地jar包
     *       cluster 必须引用集群中都存在的jar包
     * yarn模式，分为两种：
     *       client
     *       cluster
     *
     *  mesos/k8s:在mesos或k8s集群运行。
     *
     *
     * bin/spark-submit
     *spark-submit
     * --deploy-mode cluster
     * --master spark://master:7077
     * --class com.bbas.spark.chapter6.SparkClusterSubmit
     * --name submittest
     * --conf spark.default.parallesilm 2
     * --driver-memory 2g
     * --driver-cores 1
     * --executor-memory 2g
     * --executor-cores 1
     * --total-executor-cores 2 // 一共会启动两个executor
     * /homt/tomcat/SparkClusterSubmit.jar
     *
     * cluster和client最大的区分： driver线程的运行位置
     * cluster：将driver线程放在资源集群提供的容器中运行。
     * driver：切分stage,生成task,发送task到executor
     * droverwarrper进程，容器中运行，driver模块
     *
     * client:driver直接在sparksubmit进程中被启动。
     *
     * SparkSubmit(进程)
       应用提交的客户端程序
       Driver(线程)
       含有 SparkContext 实例的那个线程(功能:做stage切分，生成taskset，调度task)
       AppMaster(进程，yarn-cluster模式下才有）
       类同于MapReduce 中的MRAppMaster(local模式中是LocalJobRunner)
       负责申请启动executor,及调用driver线程功能。
        Spark程序运行起来后一堆 Task的管理者(它是一个功能模块)
        这个功能模块可以独立运行(yarn-cluster)，也可以嵌入在 Driver中(SparkContext)运行(Client 模式或 local 模式)

       Executorlauncher(yarn-client模式下有)
       在 yarn-client 模式下，作为一个简化的 appmaster 运行在容器中，只负贵申请容器资源:

       Executor（进程）
       Executor是Task的具体执行者;它负贵调用 task 中的runTask方法米执行task的运算逻辑
       (executor是进程，task是被线程所调用的对象)

     task对象
     task中就是封装了对finalrdd的迭代器进行循环调用的逻辑。
     task分为shufflemaptask和resulttask两种task。

     shufflemaptask:
     往下游输出，是用shufflewriter写shuffle溢出文件
     resulttask
     往下游输出，使用外部存储系统的客户端api向外部系统输出最终结果。




     1.spark-submit启动，
     2.向resourcemanager申请创建应用application，
     3.nodemanager领取任务，创建容器。resourcemanager返回一个applicationid及可用资源
     4.向hdfs上传程序运行所需要的各类文件资源，../staging/application_id/  spark jar包，spark配置文件，依赖jar包，缓存文件
     5.设置容器的env
     6.根据客户端env要求，下载所需文件
     7.发送一个启动applicationmaster的shell命令
     8.applicationmaster启动一个driver线程,初始化sc
     9.applicationmaster申请运行executor所需要的容器
     10.不同机器上创建CoarseGrainedExecutorBackend（构造exector对象并调用执行它的方法）
     11.exector向driver注册
     12.driver中taskscheduler向各个exector发送task

     */


  }

}
