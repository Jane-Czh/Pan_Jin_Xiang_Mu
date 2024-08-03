import request from '@/utils/request'

// 查询变更单留存列表
export function listChangeorder(query) {
  return request({
    url: '/Technology/Changeorder/list',
    method: 'get',
    params: query
  })
}

// 查询变更单留存详细
export function getChangeorder(changetableId) {
  return request({
    url: '/Technology/Changeorder/' + changetableId,
    method: 'get'
  })
}

// 新增变更单留存
export function addChangeorder(data) {
  return request({
    url: '/Technology/Changeorder',
    method: 'post',
    data: data
  })
}

// 修改变更单留存
export function updateChangeorder(data) {
  return request({
    url: '/Technology/Changeorder',
    method: 'put',
    data: data
  })
}

// 删除变更单留存
export function delChangeorder(changetableId) {
  return request({
    url: '/Technology/Changeorder/' + changetableId,
    method: 'delete'
  })
}

export function uploadFile(formData) {
  return request({
    url: '/Technology/Changeorder/FOimport',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    withCredentials: true,
    data: formData,
  });
}
