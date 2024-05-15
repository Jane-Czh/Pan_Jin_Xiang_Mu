import request from '@/utils/request'





// 一线从业人数 指标49
export function getEmployeesNumberData(data) {
  return request({
    url: `/enterprise/display/employeesNumber`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 公司平均从业人数（月度） 指标51
export function getEmployeesAVGMonthlyNumberData(data) {
  return request({
    url: `/enterprise/display/employeesAVGMonthlyNumber`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
