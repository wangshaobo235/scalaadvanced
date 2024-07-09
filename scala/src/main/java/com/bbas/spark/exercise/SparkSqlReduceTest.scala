package com.bbas.spark.exercise

import org.apache.spark.sql.SparkSession

object SparkSqlReduceTest {
  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkSession.builder()
      .appName("SparkSqlReduceTest")
      .config("spark.sql.shuffle.partitions", 36)
      .config("spark.sql.files.maxPartitionBytes", 134217728)
      .enableHiveSupport()
      .getOrCreate()

    println("######删除7月分区数据######")
    session.sql("alter table ads.t_ads_d_bi_allprovince_suppchaindata drop if exists partition(year_month='2024-07')")
    println("######7月分区数据删除完成，开始计算######")

    session.sql(
      """
        |create table ads.t_ads_d_bi_SparkSqlReduceTest
        |as
        |select
        |date_format(result.offerTime,'yyyy-MM') AS offerMonth,
        |result.provincename,
        |result.COMPANY_NAME,
        |result.BRANDNAME,
        |sum(case when result.partType in ('01','04') then result.prics else 0 end) as total_oem_prics,
        |sum(case when result.partType in ('01','04') then result.guidePrice else 0 end) as total_oem_guidePrice,
        |sum(case when result.partType not in ('01','04') then result.prics else 0 end) as total_brand_prics,
        |sum(case when result.partType not in ('01','04') then result.guidePrice else 0 end) as total_brand_guidePrice
        | from
        |(select
        |        jsd.BRANDNAME,
        |        jse.SYID,
        |        jse.SUPPNAME,
        |        jse.offerTime,
        |        coreuser.company_name,
        |        jsd.PROVINCECODE,
        |        dbp.provincename,
        |        jsdpp.enquiryPartId,
        |        jsdpp.damagePartId,
        |        jsdpp.status,jsdpp.comfigStatus,
        |        jse.confirmtime,
        |jsdpp.partType ,cast(jsdpp.prics as decimal(32,4)) as prics,cast(jopa.guidePrice as  decimal(32,4)) as guidePrice,
        |row_number()over(partition by jsdpp.enquiryPartId,jsdpp.partType order by jsdpp.id desc ) as rn
        |  from dwd.t_dwd_d_lrq_shop_damage jsd
        |  join dwd.t_dwd_d_lrq_shop_enquiry jse on jsd.DAMAGE_ID= jse.DAMAGE_ID
        |  join dwd.t_dwd_d_lrq_shop_enquiry_parts jsep on jse.enquiry_ID= jsep.enquiry_ID
        |  join dwd.t_dwd_d_lrq_shop_damage_parts_prices jsdpp on jsep.enquiry_PARTS_ID = jsdpp.enquiryPartId
        |  join dwd.t_dwd_d_lrq_shop_damage_parts jsdp on jsdpp.damagePartId = jsdp.DAMAGE_PARTS_ID
        |  join dwd.t_dwd_d_lrq_original_parts_attached jopa on jsdp.partsId = jopa.partsId
        |  join dim.dim_bc_province dbp on jsd.provincecode = dbp.provincecode
        |  join dwd.t_dwd_pu_core_user coreuser on coreuser.user_id = jse.syid
        |where jse.offerTime  BETWEEN '2024-07-01 00:00:00' and '2024-07-08 23:59:59'
        |   and jsd.channelSource= 'PICC'
        |   and jse.enquiryStatus!= '10'
        |   and jse.enquiryStatus in ('1','0','4') and jsep.enquiry_STATUS in ('0','3')
        |and cast(jopa.guidePrice as decimal(32,2))>0 and cast(jsdpp.prics as decimal(32,2))>0
        |) result
        |where result.rn = 1
        |group by
        |date_format(result.offerTime,'yyyy-MM'),
        |result.PROVINCENAME,
        |result.COMPANY_NAME,
        |result.BRANDNAME
        |""".stripMargin)

    session.close()

  }

}
