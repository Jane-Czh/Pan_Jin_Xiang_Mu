import request from '@/utils/request'

// 查询制度执行率列表
export function listRegulationsImplementationRate(query) {
  return request({
    url: '/file/regulationsImplementationRate/list',
    method: 'get',
    params: query
  })
}

// 查询制度执行率详细
export function getRegulationsImplementationRate(id) {
  return request({
    url: '/file/regulationsImplementationRate/' + id,
    method: 'get'
  })
}

// 新增制度执行率
export function addRegulationsImplementationRate(data) {
  return request({
    url: '/file/regulationsImplementationRate',
    method: 'post',
    data: data
  })
}

// 修改制度执行率
export function updateRegulationsImplementationRate(data) {
  return request({
    url: '/file/regulationsImplementationRate',
    method: 'put',
    data: data
  })
}

// 删除制度执行率
export function delRegulationsImplementationRate(id) {
  return request({
    url: '/file/regulationsImplementationRate/' + id,
    method: 'delete'
  })
}

// 获取制度执行率信息
export function getRegulationsImplementationRateByData(data) {
  return request({
    url: `/file/regulationsImplementationRate/implementationRateCounts`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime,
    }
  })
}
