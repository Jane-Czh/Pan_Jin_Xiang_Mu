import request from '@/utils/request'

// 查询售后台账导入列表
export function listLedger(query) {
  return request({
    url: '/market/ledger/list',
    method: 'get',
    params: query
  })
}

// 查询售后台账导入详细
export function getLedger(maslId) {
  return request({
    url: '/market/ledger/' + maslId,
    method: 'get'
  })
}

// 新增售后台账导入
export function addLedger(data) {
  return request({
    url: '/market/ledger',
    method: 'post',
    data: data
  })
}

// 修改售后台账导入
export function updateLedger(data) {
  return request({
    url: '/market/ledger',
    method: 'put',
    data: data
  })
}

// 删除售后台账导入
export function delLedger(maslId) {
  return request({
    url: '/market/ledger/' + maslId,
    method: 'delete'
  })
}

//导入XX excel文件
export function uploadImport(formData) {
  return request({
    url: '/market/ledger/import',
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