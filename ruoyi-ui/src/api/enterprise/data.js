import request from '@/utils/request'


export function listMonthData(query) {
  return request({
    url: '/enterprise/Data/list',
    method: 'get',
    params: query
  })
}

// 查询[企业管理]指标月度数据详细
export function getMonthData(esId) {
  return request({
    url: '/enterprise/Data/' + esId,
    method: 'get'
  })
}

// 新增[企业管理]指标月度数据
export function addMonthData(data) {
  return request({
    url: '/enterprise/Data',
    method: 'post',
    data: data
  })
}

// 修改[企业管理]指标月度数据
export function updateMonthData(data) {
  return request({
    url: '/enterprise/Data',
    method: 'put',
    data: data
  })
}

// 删除[企业管理]指标月度数据
export function delMonthData(esIds) {
  return request({
    url: '/enterprise/Data/' + esIds,
    method: 'delete'
  })
}


// 查询[企业管理]指标年度数据列表
export function listYearData(query) {
  return request({
    url: '/enterprise/AnnualData/list',
    method: 'get',
    params: query
  })
}

// 查询[企业管理]指标年度数据详细
export function getYearData(emasId) {
  return request({
    url: '/enterprise/AnnualData/' + emasId,
    method: 'get'
  })
}

// 新增[企业管理]指标年度数据
export function addYearData(data) {
  return request({
    url: '/enterprise/AnnualData',
    method: 'post',
    data: data
  })
}

// 修改[企业管理]指标年度数据
export function updateYearData(data) {
  return request({
    url: '/enterprise/AnnualData',
    method: 'put',
    data: data
  })
}

// 删除[企业管理]指标年度数据
export function delYearData(emasId) {
  return request({
    url: '/enterprise/AnnualData/' + emasId,
    method: 'delete'
  })
}


