import request from '@/utils/request'

// 查询物料类别字典存储列表
export function listCategorydicTable(query) {
  return request({
    url: '/supply/categorydicTable/list',
    method: 'get',
    params: query
  })
}

// 查询物料类别字典存储详细
export function getCategorydicTable(smcdId) {
  return request({
    url: '/supply/categorydicTable/' + smcdId,
    method: 'get'
  })
}

// 新增物料类别字典存储
export function addCategorydicTable(data) {
  return request({
    url: '/supply/categorydicTable',
    method: 'post',
    data: data
  })
}

// 修改物料类别字典存储
export function updateCategorydicTable(data) {
  return request({
    url: '/supply/categorydicTable',
    method: 'put',
    data: data
  })
}

// 删除物料类别字典存储
export function delCategorydicTable(smcdId) {
  return request({
    url: '/supply/categorydicTable/' + smcdId,
    method: 'delete'
  })
}

//导入XX excel文件
export function uploadImport(formData) {
  return request({
    url: '/supply/categorydicTable/import',
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