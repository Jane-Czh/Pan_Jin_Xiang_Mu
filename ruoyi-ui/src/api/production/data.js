import request from '@/utils/request'

// 查询[生产]手动填报指标列表
export function listData(query) {
  return request({
    url: '/production/FillingInIndicators/list',
    method: 'get',
    params: query
  })
}

// 查询[生产]手动填报指标详细
export function getData(productionId) {
  return request({
    url: `/production/FillingInIndicators/${productionId}`,
    method: 'get'
  })
}

// 新增[生产]手动填报指标
export function addData(data) {
  // const data = {
  //   yearAndMonth: year + '-' + month 
  // };
  return request({
    url: '/production/FillingInIndicators',
    method: 'post',
    data: data
  });
}


// 修改[生产]手动填报指标
export function updateData(data) {
  return request({
    url: '/production/FillingInIndicators',
    method: 'put',
    data: data
  })
}

// 删除[生产]手动填报指标
export function delData(productionIds) {
  return request({
    url: `/production/FillingInIndicators/${productionIds}`,
    method: 'delete'
  })
}




// 当月单台非BOM物料费用24
export function getcurNonBomMaterialCostData(data) {
  return request({
    url: `/production/display/curNonBomMaterialCost`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月单台低值易耗费用 指标25
export function getcurLowValueConsumablesData(data) {
  return request({
    url: `/production/display/curLowValueConsumables`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 在制物资年化周转天数 指标29
export function getinventoryTurnoverdaysData(data) {
  return request({
    url: `/production/display/inventoryTurnoverdays`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 人均生产台数 指标37
export function getoutputPercapitacountsData(data) {
  return request({
    url: `/production/display/outputPercapitacounts`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 人均产值 指标38
export function getoutputPercapitavalueData(data) {
  return request({
    url: `/production/display/outputPercapitavalue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 上线及时率 指标41
export function getonlineOntimerateData(data) {
  return request({
    url: `/production/display/onlineOntimerate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 一线当月加班时长 指标48
export function getOvertimeFrontlinemonthData(data) {
  return request({
    url: `/production/display/overtimeFrontlinemonth`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
