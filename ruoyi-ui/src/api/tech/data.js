import request from '@/utils/request'


// 查询[技术]手动填报指标列表
export function listData(query) {
  return request({
    url: '/tech/data/list',
    method: 'get',
    params: query
  })
}

// 查询[技术]手动填报指标详细
export function getData(techId) {
  return request({
    url: `/tech/data/${techId}`,
    method: 'get'
  })
}

// 新增[技术]手动填报指标
export function addData(data) {
  return request({
    url: '/tech/data/monthly',
    method: 'post',
    data: data
  })
}

// 新增[技术]总计划年初填报
export function addAnnualData(data) {
  return request({
    url: '/tech/data/annual',
    method: 'post',
    data: data
  })
}


// 修改[技术]手动填报指标
export function updateData(data) {
  return request({
    url: '/tech/data',
    method: 'put',
    data: data
  })
}

// 删除[技术]手动填报指标
export function delData(techIds) {
  return request({
    url: `/financial/data/${techIds}`,
    method: 'delete'
  })
}








// 非标订单平均技术准备天数 指标47
export function getEmployeesAVGMonthlyNumberData(data) {
  return request({
    url: `/tech/display/employeesAVGMonthlyNumber`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 研发项目计划进度完成率 指标71
export function getPrdScheduleCompletionRateData(data) {
  return request({
    url: `/tech/display/prdScheduleCompletionRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}