import request from '@/utils/request'

const api = {
  // 当月单台非BOM物料费用24
  getcurNonBomMaterialCostData(data) {
    return request({
      url: `/production/display/curNonBomMaterialCost`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月单台低值易耗费用 指标25
  getcurLowValueConsumablesData(data) {
    return request({
      url: `/production/display/curLowValueConsumables`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 在制物资年化周转天数 指标29
  getinventoryTurnoverdaysData(data) {
    return request({
      url: `/production/display/inventoryTurnoverdays`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 人均生产台数 指标37
  getoutputPercapitacountsData(data) {
    return request({
      url: `/production/display/outputPercapitacounts`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 人均产值 指标38
  getoutputPercapitavalueData(data) {
    return request({
      url: `/production/display/outputPercapitavalue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 上线及时率 指标41
  getonlineOntimerateData(data) {
    return request({
      url: `/production/display/onlineOntimerate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 一线当月加班时长 指标48
  getOvertimeFrontlinemonthData(data) {
    return request({
      url: `/production/display/overtimeFrontlinemonth`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  }
}

//日上线数
export function getDailyLaunchAndCompletionData(data) {
  return request({
    url: `/production/display/dailyLaunchAndCompletion`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

//月上线数
export function getMonthlyLaunchAndCompletionData(data) {
  return request({
    url: `/production/display/monthlyLaunchAndCompletion`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

//年上线数
export function getAnnualLaunchAndCompletionData(data) {
  return request({
    url: `/production/display/annualLaunchAndCompletion`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

export default api