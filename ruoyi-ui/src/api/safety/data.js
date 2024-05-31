import request from '@/utils/request'

// 查询[安全环保]指标填报列表
export function listData(query) {
  return request({
    url: '/safety/data/list',
    method: 'get',
    params: query
  })
}

// 查询[安全环保]指标填报详细
export function getData(safetyEpId) {
  return request({
    url: '/safety/data/' + safetyEpId,
    method: 'get'
  })
}

// 新增[安全环保]指标填报
export function addData(data) {
  return request({
    url: '/safety/data',
    method: 'post',
    data: data
  })
}

// 修改[安全环保]指标填报
export function updateData(data) {
  return request({
    url: '/safety/data',
    method: 'put',
    data: data
  })
}

// 删除[安全环保]指标填报
export function delData(safetyEpId) {
  return request({
    url: '/safety/data/' + safetyEpId,
    method: 'delete'
  })
}



// 当月度设备维修总费用 指标23
export function getCurEquipmentMaintenanceCostData(data) {
  return request({
    url: `/safetyEp/display/curEquipmentMaintenanceCost`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 重点设备故障率 指标45
export function getKeyEquipmentFailureRateData(data) {
  return request({
    url: `/safetyEp/display/keyEquipmentFailureRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月设备故障累计停产时间 指标46
export function getCurEquipmentFailuresTotaltimeData(data) {
  return request({
    url: `/safetyEp/display/curEquipmentFailuresTotaltime`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 主要设备故障总次数 指标67
export function getKeyEquipmentTotalFailureCountData(data) {
  return request({
    url: `/safetyEp/display/keyEquipmentTotalFailureCount`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
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



// 当月设备维修成本-替换件成本 指标69
export function getCurEquipmentReplacementCostData(data) {
  return request({
    url: `/safetyEp/display/curEquipmentReplacementCost`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


