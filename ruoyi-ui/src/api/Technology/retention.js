import request from '@/utils/request'

// 查询申报材料统计列表
export function listRetention(query) {
  return request({
    url: '/Technology/retention/list',
    method: 'get',
    params: query
  })
}
// 查询历史文件列表
export function getRegulationsHistory(currentId) {
  return request({
    url: '/Technology/retention/history/' + currentId,
    method: 'get',
    // params: query
  });
}
// 查询申报材料统计详细
export function getRetention(tfemId) {
  return request({
    url: '/Technology/retention/' + tfemId,
    method: 'get'
  })
}

// 新增申报材料统计
export function addRetention(data) {
  return request({
    url: '/Technology/retention',
    method: 'post',
    data: data
  })
}

// 修改申报材料统计
export function updateRetention(data) {
  return request({
    url: '/Technology/retention',
    method: 'put',
    data: data
  })
}

// 删除申报材料统计
export function delRetention(tfemId) {
  return request({
    url: '/Technology/retention/' + tfemId,
    method: 'delete'
  })
}

// word转pdf
// export function word2Pdf(inPath, outPath) {
//   return request({
//     url: '/Technology/retention/convert',
//     method: 'post',
//     params: { inPath, outPath }
//   });
// }
