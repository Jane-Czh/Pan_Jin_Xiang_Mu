import request from '@/utils/request'

// 查询[质量]指标填报列表
export function listHandFill(query) {
  return request({
    url: '/quality/handFill/list',
    method: 'get',
    params: query
  })
}

// 查询[质量]指标填报详细
export function getHandFill(qihfId) {
  return request({
    url: '/quality/handFill/' + qihfId,
    method: 'get'
  })
}

// 新增[质量]指标填报
export function addHandFill(data) {
  return request({
    url: '/quality/handFill',
    method: 'post',
    data: data
  })
}

// 修改[质量]指标填报
export function updateHandFill(data) {
  return request({
    url: '/quality/handFill',
    method: 'put',
    data: data
  })
}

// 删除[质量]指标填报
export function delHandFill(qihfId) {
  return request({
    url: '/quality/handFill/' + qihfId,
    method: 'delete'
  })
}


// 质量考核季度排名 指标63
export function getQuarterlyRankData(data) {
  return request({
    url: `/quality/display/quarterlyRank`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 平均无故障时间 指标64
export function getMeantimeWithoutFailureData(data) {
  return request({
    url: `/quality/display/meantimeWithoutFailure`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}