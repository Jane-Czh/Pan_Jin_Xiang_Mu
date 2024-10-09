import request from '@/utils/request'

// 查询指标-目标值列表
export function listTarget(query) {
  return request({
    url: '/indicators/target/list',
    method: 'get',
    params: query
  })
}

// 查询指标-目标值详细
export function getTarget(itId) {
  return request({
    url: '/indicators/target/' + itId,
    method: 'get'
  })
}

// 新增指标-目标值
export function addTarget(data) {
  return request({
    url: '/indicators/target',
    method: 'post',
    data: data
  })
}

// 修改指标-目标值
export function updateTarget(data) {
  return request({
    url: '/indicators/target',
    method: 'put',
    data: data
  })
}

// 删除指标-目标值
export function delTarget(itId) {
  return request({
    url: '/indicators/target/' + itId,
    method: 'delete'
  })
}

// 查询当年目标值
export function getTargetData(data) {
  return request({
    url: '/indicators/target/allTargetInfoByDept',
    method: 'post',
    data: {
      date: data.date,
      // date: data.endTime,
      deptName: data.deptName
    }
  })
}


// 查询当年目标值
export function getNameTarget(data) {
  return request({
    url: '/indicators/target/targetInfoByIndicator',
    method: 'post',
    data: {
      name: data.name,
      startDate: data.startDate,
      endDate: data.endDate
    }
  })
}
