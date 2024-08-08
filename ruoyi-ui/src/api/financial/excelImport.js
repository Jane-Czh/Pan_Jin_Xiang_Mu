
import request from '@/utils/request'


export function uploadFile(data, aimUrl) {
  return request({
    url: aimUrl,
    method: 'post',
    data: data
  })
}


export function handleTrueDownload(url) {
  return window.location.href = "http://localhost:8080" + url;
}


//非标订单
export function checkExist(data) {
  return request({
    url: `/tech/data/nonStandardOrder/check`,
    method: 'post',
    data: data
  })
}
//项目进度
export function checkExistProject(data) {
  return request({
    url: `/tech/data/newProject/check`,
    method: 'post',
    data: data
  })
}

