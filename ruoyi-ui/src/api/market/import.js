import request from '@/utils/request'

// 查询市场科销售台账列表
export function listImport(query) {
  return request({
    url: '/market/import/list',
    method: 'get',
    params: query
  })
}

// 查询市场科销售台账详细
export function getImport(msId) {
  return request({
    url: '/market/import/' + msId,
    method: 'get'
  })
}

// 新增市场科销售台账
export function addImport(data) {
  return request({
    url: '/market/import',
    method: 'post',
    data: data
  })
}

// 修改市场科销售台账
export function updateImport(data) {
  return request({
    url: '/market/import',
    method: 'put',
    data: data
  })
}

// 删除市场科销售台账
export function delImport(msId) {
  return request({
    url: '/market/import/' + msId,
    method: 'delete'
  })
}

//导入XX excel文件
export function uploadImport(formData) {
  return request({
    url: '/market/import/import',
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

