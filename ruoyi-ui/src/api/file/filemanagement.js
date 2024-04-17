import request from '@/utils/request'

// 查询文件管理列表
export function listFilemanagement(query) {
  return request({
    url: '/file/filemanagement/list',
    method: 'get',
    params: query
  })
}

// 查询文件管理详细
export function getFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'get'
  })
}

// 新增文件管理
export function addFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'post',
    data: data
  })
}

// 修改文件管理
export function updateFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'put',
    data: data
  })
}

// 删除文件管理
export function delFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'delete'
  })
}


function submitHandler() {
  if ($.validate.form()) {
    uploadFile();
  }
}

function uploadFile() {
  var formData = new FormData();
  if ($('#filePath')[0].files[0] == null) {
    $.modal.alertWarning("请先选择文件路径");
    return false;
  }
  formData.append('fileName', $("#fileName").val());
  formData.append('file', $('#filePath')[0].files[0]);
  $.ajax({
    url: prefix + "/add",
    type: 'post',
    cache: false,
    data: formData,
    processData: false,
    contentType: false,
    dataType: "json",
    success: function(result) {
      $.operate.successCallback(result);
    }
  });
}
