import request from '@/utils/request'

// 查询[党建]指标填报列表
export function listData(query) {
  return request({
    url: '/partybuilding/data/list',
    method: 'get',
    params: query
  })
}

// 查询[党建]指标填报详细
export function getData(pbId) {
  return request({
    url: '/partybuilding/data/' + pbId,
    method: 'get'
  })
}

// 新增[党建]指标填报
export function addData(data) {
  return request({
    url: '/partybuilding/data',
    method: 'post',
    data: data
  })
}

// 修改[党建]指标填报
export function updateData(data) {
  return request({
    url: '/partybuilding/data',
    method: 'put',
    data: data
  })
}

// 删除[党建]指标填报
export function delData(pbId) {
  return request({
    url: '/partybuilding/data/' + pbId,
    method: 'delete'
  })
}

// 党建月度考核排名 指标50
export function getPartyBuildingRankData(data) {
  return request({
    url: `/partybuilding/display/partyBuildingRank`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
