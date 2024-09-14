import request from '@/utils/request'


// 查询[技术]指标填报列表
export function listData(query) {
  return request({
    url: '/tech/data/monthly/list',
    method: 'get',
    params: query
  })
}

// 查询[技术]指标填报详细
export function getData(techId) {
  return request({
    url: '/tech/data/monthly/' + techId,
    method: 'get'
  })
}

// 新增[技术]指标填报
export function addData(data) {
  return request({
    url: '/tech/data/monthly',
    method: 'post',
    data: data
  })
}

// 修改[技术]指标填报
export function updateData(data) {
  return request({
    url: '/tech/data/monthly',
    method: 'put',
    data: data
  })
}

// 删除[技术]指标填报
export function delData(techId) {
  return request({
    url: '/tech/data/monthly/' + techId,
    method: 'delete'
  })
}


// 年度指标填报

// 查询【技术】总计划年初填报列表
export function listData2(query) {
  return request({
    url: '/tech/data/annual/list',
    method: 'get',
    params: query
  })
}

// 查询【技术】总计划年初填报详细
export function getData2(tapcId) {
  return request({
    url: '/tech/data/annual/' + tapcId,
    method: 'get'
  })
}

// 新增【技术】总计划年初填报
export function addData2(data) {
  return request({
    url: '/tech/data/annual',
    method: 'post',
    data: data
  })
}

// 修改【技术】总计划年初填报
export function updateData2(data) {
  return request({
    url: '/tech/data/annual',
    method: 'put',
    data: data
  })
}

// 删除【技术】总计划年初填报
export function delData2(tapcId) {
  return request({
    url: '/tech/data/annual/' + tapcId,
    method: 'delete'
  })
}




// 非标订单平均技术准备天数 指标47
export function getNonStandardAVGPreparationDaysData(data) {
  return request({
    url: `/tech/display/nonStandardAVGPreparationDays`,
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



// 更新[技术]指标填报状态
export function updateList(data) {
  return request({
    url: '/tech/data/update',
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

