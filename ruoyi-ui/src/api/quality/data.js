import request from '@/utils/request'




// 质量考核季度排名 指标63
export function getQuarterlyRankData(data) {
  return request({
    url: `/quality/display/quarterlyRank`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 平均无故障时间 指标64
export function getMeantimeWithoutFailureData(data) {
  return request({
    url: `/quality/display/meantimeWithoutFailure`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}