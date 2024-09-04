import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listSumNumber(query) {
  return request({
    url: '/system/sumNumber/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getSumNumber(id) {
  return request({
    url: '/system/sumNumber/' + id,
    method: 'get'
  })
}

// 导入【请填写功能名称】
export function uploadImport(formData) {
  return request({
    url: '/system/sumNumber/import',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    withCredentials: true,
    data: formData,
    onUploadProgress: (progressEvent) => {
      let progress = Math.round(
        (progressEvent.loaded * 100) / progressEvent.total
      );
      // 这里可以处理上传进度，例如通过事件发射给外部组件
      // this.$emit('progress', progress);
    },
  });
}
// 新增【请填写功能名称】
export function addSumNumber(data) {
  return request({
    url: '/system/sumNumber',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateSumNumber(data) {
  return request({
    url: '/system/sumNumber',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delSumNumber(id) {
  return request({
    url: '/system/sumNumber/' + id,
    method: 'delete'
  })
}
