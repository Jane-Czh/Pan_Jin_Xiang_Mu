import request from '@/utils/request'

// 查询主要车型维护列表
export function listCartype(query) {
  return request({
    url: '/market/cartype/list',
    method: 'get',
    params: query
  })
}

// 查询主要车型维护详细
export function getCartype(mctId) {
  return request({
    url: '/market/cartype/' + mctId,
    method: 'get'
  })
}

// 新增主要车型维护
export function addCartype(data) {
  return request({
    url: '/market/cartype',
    method: 'post',
    data: data
  })
}

// 修改主要车型维护
export function updateCartype(data) {
  return request({
    url: '/market/cartype',
    method: 'put',
    data: data
  })
}

// 删除主要车型维护
export function delCartype(mctId) {
  return request({
    url: '/market/cartype/' + mctId,
    method: 'delete'
  })
}

//导入 excel文件
export function uploadImport(formData) {
  return request({
    url: '/market/cartype/import',
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