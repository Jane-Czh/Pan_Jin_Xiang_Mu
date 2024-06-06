import request from '@/utils/request'

// 查询[财务]每日填报指标[当日再制品金额]列表
export function listData2(query) {
  return request({
    url: '/financial/data/dailyInProgress/list',
    method: 'get',
    params: query
  })
}

// 查询[财务]每日填报指标[当日再制品金额]详细
export function getData2(id) {
  return request({
    url: '/financial/data/dailyInProgress/' + id,
    method: 'get'
  })
}

// 新增[财务]每日填报指标[当日再制品金额]
export function addData2(data) {
  return request({
    url: '/financial/data/dailyInProgress',
    method: 'post',
    data: data
  })
}

// 修改[财务]每日填报指标[当日再制品金额]
export function updateData2(data) {
  return request({
    url: '/financial/data/dailyInProgress',
    method: 'put',
    data: data
  })
}

// 删除[财务]每日填报指标[当日再制品金额]
export function delData2(id) {
  return request({
    url: '/financial/data/dailyInProgress/' + id,
    method: 'delete'
  })
}