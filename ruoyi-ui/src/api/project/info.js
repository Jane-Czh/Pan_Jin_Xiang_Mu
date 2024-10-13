import request from '@/utils/request'

// 查询项目基本信息列表
export function listInfo(query) {
  return request({
    url: '/project/Info/list',
    method: 'get',
    params: query
  })
}

// 查询项目基本信息详细
export function getProjectInfo(projectId) {
  return request({
    url: '/project/Info/' + projectId,
    method: 'get'
  })
}

// 新增项目基本信息
export function addInfo(data) {
  return request({
    url: '/project/Info',
    method: 'post',
    data: data
  })
}

// 修改项目基本信息
export function updateInfo(data) {
  return request({
    url: '/project/Info',
    method: 'put',
    data: data
  })
}

// 修改项目历史项目关联
export function updateInfoHistory(data) {
  return request({
    url: '/project/Info/updataHistory',
    method: 'put',
    data: data
  })
}

// 删除项目基本信息
export function delInfo(projectId) {
  return request({
    url: '/project/Info/' + projectId,
    method: 'delete'
  })
}

// 移入回收站
export function recycleInfo(projectId) {
  return request({
    url: '/project/Info/recycle' + projectId,
    method: 'delete'
  })
}


export function Recodingquery(projectName) {
  return request({
    url: '/project/Info/recoding/' + projectName,
    method: 'get'
  })
}

//导入 excel文件
export function uploadImport(formData) {
  return request({
    url: '/project/Info/import',
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

export function handleTrueDownload(url) {
  return window.location.href = "http://10.36.53.24:8080" + url;
  // return window.location.href = "http://localhost:8080" + url;
}
