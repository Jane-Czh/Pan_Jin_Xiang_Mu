import request from '@/utils/request'

// 查询文件管理列表
export function listFilemanagement(query) {
  return request({
    url: '/file/filemanagement/list',
    method: 'get',
    params: query
  })
}

// 查询历史文件列表
export function getRegulationsHistory(currentId) {
  return request({
    url: '/file/filemanagement/history/' + currentId,
    method: 'get',
    // params: query
  });
}

// 查询文件管理详细
export function getFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'get'
  })
}

// 新增文件管理
export function addFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'post',
    data: data
  })
}

// 修改文件管理
export function updateFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'put',
    data: data
  })
}

// 删除文件管理
export function delFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'delete'
  })
}

// 修改文件管理
export function getLastId(data) {
  return request({
    url: '/file/filemanagement',
    method: 'put',
    data: data
  })
}

// word转pdf
export function word2Pdf(inPath, outPath) {
  return request({
    url: '/file/filemanagement/convert',
    method: 'post',
    params: { inPath, outPath }
  });
}

// 指标:制度修订频率
export function getRevisionCounts(data) {
  return request({
    url: `/file/filemanagement/revisionCounts`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime,
      regulationsId: data.regulationsId,
    }
  })
}
