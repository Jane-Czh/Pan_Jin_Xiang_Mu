import request from '@/utils/request'

// 查询主要车型字典表列表
export function listMainCarType(query) {
  return request({
    url: '/market/mainCarType/list',
    method: 'get',
    params: query
  })
}

// 查询主要车型字典表详细
export function getMainCarType(id) {
  return request({
    url: '/market/mainCarType/' + id,
    method: 'get'
  })
}

// 新增主要车型字典表
export function addMainCarType(data) {
  return request({
    url: '/market/mainCarType',
    method: 'post',
    data: data
  })
}

// 修改主要车型字典表
export function updateMainCarType(data) {
  return request({
    url: '/market/mainCarType',
    method: 'put',
    data: data
  })
}

// 删除主要车型字典表
export function delMainCarType(id) {
  return request({
    url: '/market/mainCarType/' + id,
    method: 'delete'
  })
}

//导入XX excel文件
export function uploadImport(formData) {
  return request({
    url: '/market/mainCarType/import',
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