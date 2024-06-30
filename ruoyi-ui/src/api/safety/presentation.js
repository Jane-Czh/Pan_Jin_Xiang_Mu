import request from '@/utils/request'

// 查询成绩考核展示列表
export function listPresentation(query) {
  return request({
    url: '/safety/presentation/list',
    method: 'get',
    params: query
  })
}

// 查询成绩考核展示详细
export function getPresentation(searpId) {
  return request({
    url: '/safety/presentation/' + searpId,
    method: 'get'
  })
}

// 新增成绩考核展示
export function addPresentation(data) {
  return request({
    url: '/safety/presentation',
    method: 'post',
    data: data
  })
}

// 修改成绩考核展示
export function updatePresentation(data) {
  return request({
    url: '/safety/presentation',
    method: 'put',
    data: data
  })
}

// 删除成绩考核展示
export function delPresentation(searpId) {
  return request({
    url: '/safety/presentation/' + searpId,
    method: 'delete'
  })
}

export function synchronization(){
  return request({
    url: '/safety/presentation/synchronization',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },

  });
}