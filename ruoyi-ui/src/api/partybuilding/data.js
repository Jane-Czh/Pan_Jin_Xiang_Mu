import request from '@/utils/request'



// 党建月度考核排名 指标50
export function getPartyBuildingRankData(data) {
  return request({
    url: `/partybuilding/display/partyBuildingRank`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
