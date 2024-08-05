import request from '@/utils/request'


// 班组自查合格率 下道工序反馈合格率 指标104+105
export function getPartQualificationRateData(data) {
  return request({
    url: `/quality/display/partQualificationRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 电车、大吨位一次交检合格率 指标102
export function getSingleInspectionPassRateData(data) {
  return request({
    url: `/quality/display/singleInspectionPassRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


const api = {
  // 三包期内新车返修率 指标57
  getWarrantyRepairRateData(data) {
    return request({
      url: `/quality/display/warrantyRepairRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 三包期内整车月度返修率 指标58
  getWarrantyVehicleRepairRateData(data) {
    return request({
      url: `/quality/display/warrantyVehicleRepairRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 外部质量损失率 指标59
  getExternalLossRateData(data) {
    return request({
      url: `/quality/display/externalLossRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 三包期内新车返修率 指标61
  getMonthlyAfterSalesIssuesData(data) {
    return request({
      url: `/quality/display/monthlyAfterSalesIssues`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 售后问题生产责任次数 指标62
  getProductionLiabilityIssuesData(data) {
    return request({
      url: `/quality/display/productionLiabilityIssues`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 质量考核季度排名 指标63
  getQuarterlyRankData(data) {
    return request({
      url: `/quality/display/quarterlyRank`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 平均无故障时间 指标64
  getMeantimeWithoutFailureData(data) {
    return request({
      url: `/quality/display/meantimeWithoutFailure`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },



  // 供应商不合格件返厂及时率 指标103
  getInTimeReturnRateData(data) {
    return request({
      url: `/quality/display/inTimeReturnRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  }



}


export default api



// 月度数据最新数据
export function getMonthNewData() {
  return request({
    url: `/quality/data/handfill/newData`,
    method: 'post',
  })
}

// 售后表指标最新数据
export function getAfterNewData() {
  return request({
    url: `/quality/data/metrics/newData`,
    method: 'post',
  })
}


// 质检表最新数据
export function getInspectionNewData() {
  return request({
    url: `/quality/data/inspection/newData`,
    method: 'post',
  })
}

