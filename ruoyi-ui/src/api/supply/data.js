import request from '@/utils/request'

//集团管控物资采购金额占比 指标31-展示
export function getControlledPurchaseAmountRatioData(data) {
  return request({
    url: `/supply/display/controlledPurchaseAmountRatio`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
