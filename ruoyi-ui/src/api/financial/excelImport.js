
import request from '@/utils/request'


export function uploadFile(data, aimUrl) {
  return request({
    url: aimUrl,
    method: 'post',
    data: data
  })
}




export function handleTrueDownload(url) {
  return window.location.href = "http://124.222.36.192:8080" + url;
}

