import request from '@/utils/request'

// 查询月供货比例统计列表
export function listRatio(query) {
  return request({
    url: '/supply/ratio/list',
    method: 'get',
    params: query
  })
}

// 查询月供货比例统计详细
export function getRatio(srftId) {
  return request({
    url: '/supply/ratio/' + srftId,
    method: 'get'
  })
}

// 新增月供货比例统计
export function addRatio(data) {
  return request({
    url: '/supply/ratio',
    method: 'post',
    data: data
  })
}

// 修改月供货比例统计
export function updateRatio(data) {
  return request({
    url: '/supply/ratio',
    method: 'put',
    data: data
  })
}

// 删除月供货比例统计
export function delRatio(srftId) {
  return request({
    url: '/supply/ratio/' + srftId,
    method: 'delete'
  })
}

export function synchronization(){
  return request({
    url: '/supply/ratio/synchronization',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },

  });
}

//导入XX excel文件
export function uploadImport(formData) {
  return request({
    url: '/supply/ratio/import',
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