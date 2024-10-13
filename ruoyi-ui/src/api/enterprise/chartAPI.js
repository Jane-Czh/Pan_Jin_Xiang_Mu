import request from '@/utils/request'

const api = {
  // 查询[企业管理]指标月度数据列表

  // 一线从业人数 指标49
  getEmployeesNumberData(data) {
    return request({
      url: `/enterprise/display/employeesNumber`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 公司平均从业人数（月度） 指标51
  getEmployeesAVGMonthlyNumberData(data) {
    return request({
      url: `/enterprise/display/employeesAVGMonthlyNumber`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 公司平均从业人数（年度）
  getEmployeesAVGAnnualNumberData(data) {
    return request({
      url: `/enterprise/display/employeesAVGAnnualNumber`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 工资总额年度占比
  getAnnualSalaryRatioData(data) {
    return request({
      url: `/enterprise/display/annualSalaryRatio`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },




  // 职能部门人均加班费用 指标76
  getFunctionalDeptOvertimeCostData(data) {
    return request({
      url: `/enterprise/display/functionalDeptOvertimeCost`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  }



}

export default api


// 日清日结 指标53
export function getDailyClearingSettlementData(data) {
  return request({
    url: `/enterprise/display/dailyClearingSettlement`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 11项管理指标 指标101
export function getManagementData(data) {
  return request({
    url: `/enterprise/display/management`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 累计人均收入 指标54
export function getCumulativeAverageIncomeData(data) {
  return request({
    url: `/enterprise/display/cumulativeAverageIncome`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 月度累计生产人均收入 指标55
export function getMonthlyProductionAVGIncomeData(data) {
  return request({
    url: `/enterprise/display/monthlyProductionAVGIncome`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 月度累计职能人均收入 指标56
export function getMonthlyFunctionalAVGIncomeData(data) {
  return request({
    url: `/enterprise/display/monthlyFunctionalAVGIncome`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 企管工资指标总览最新展示
export function getSalaryNewData() {
  return request({
    url: `/enterprise/display/salaryNewData`,
    method: 'get',
  })
}

// 企管其他指标总览最新展示
export function getMonthlyNewData() {
  return request({
    url: `/enterprise/display/monthlyNewData`,
    method: 'get',
  })
}


