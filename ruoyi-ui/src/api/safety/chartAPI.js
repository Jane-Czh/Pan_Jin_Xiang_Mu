import request from '@/utils/request'


const api = {

  // 当月度设备维修总费用 指标23
  getCurEquipmentMaintenanceCostData(data) {
    return request({
      url: `/safetyEp/display/curEquipmentMaintenanceCost`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 重点设备故障率 指标45
  getKeyEquipmentFailureRateData(data) {
    return request({
      url: `/safetyEp/display/keyEquipmentFailureRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月设备故障累计停产时间 指标46
  getCurEquipmentFailuresTotaltimeData(data) {
    return request({
      url: `/safetyEp/display/curEquipmentFailuresTotaltime`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 主要设备故障总次数 指标67
  getKeyEquipmentTotalFailureCountData(data) {
    return request({
      url: `/safetyEp/display/keyEquipmentTotalFailureCount`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月设备维修成本-替换件成本 指标69
  getCurEquipmentReplacementCostData(data) {
    return request({
      url: `/safetyEp/display/curEquipmentReplacementCost`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  }

}

// 设备故障类别次数分布图 指标68
export function getOutputPercapitavalueData(data) {
  return request({
    url: `/safetyEp/display/outputPercapitavalue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}







export default api