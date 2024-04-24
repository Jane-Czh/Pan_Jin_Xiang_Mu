import request from '@/utils/request'

// 查询[财务]手动填报指标列表
export function listData(query) {
  return request({
    url: '/financial/data/list',
    method: 'get',
    params: query
  })
}

// 查询[财务]手动填报指标详细
export function getData(fihfId) {
  return request({
    url: '/financial/data/' + fihfId,
    method: 'get'
  })
}

// 新增[财务]手动填报指标
export function addData(data) {
  return request({
    url: '/financial/data',
    method: 'post',
    data: data
  })
}

// 修改[财务]手动填报指标
export function updateData(data) {
  return request({
    url: '/financial/data',
    method: 'put',
    data: data
  })
}

// 删除[财务]手动填报指标
export function delData(fihfId) {
  return request({
    url: '/financial/data/' + fihfId,
    method: 'delete'
  })
}



// 主营业务收入
export function getMainRevenueData(data) {
  return request({
    url: `/financial/display/mainRevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 整机销售
export function getTotalSalesRevenueData(startTime, endTime) {
  return request({
    url: `/financial/display/totalSalesRevenue/?startTime=${startTime}&endTime=${endTime}`,
    method: 'post'
  })
}

// 集团外部销售收入
export function getExternalGroupSalesRevenueData(fihfId) {
  return request({
    url: `/financial/display/externalGroupSalesRevenue/?fihfId=${fihfId}`,
    method: 'get'
  })
}


// 整车产量
export function getTotalVehicleProductionData(fihfId) {
  return request({
    url: `/financial/display/totalVehicleProduction/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 整车销量
export function getTotalVehicleSalesData(fihfId) {
  return request({
    url: `/financial/display/totalVehicleSales/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 新产品销售收入
export function getNewProductSalesRevenueData(fihfId) {
  return request({
    url: `/financial/display/newProductSalesRevenue/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 特色产品收入
export function getSpecialtyProductRevenueData(fihfId) {
  return request({
    url: `/financial/display/specialtyProductRevenue/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 主营业务成本
export function getCOGSData(fihfId) {
  return request({
    url: `/financial/display/COGS/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 整机销售成本
export function getTotalSalesCostData(fihfId) {
  return request({
    url: `/financial/display/totalSalesCost/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 净利润
export function getNetProfitData(fihfId) {
  return request({
    url: `/financial/display/netProfit/?fihfId=${fihfId}`,
    method: 'get'
  })
}

//20当月管理费用
export function getManagementExpenseMonthData(fihfId) {
  return request({
    url: `/financial/display/managementExpenseMonth/?fihfId=${fihfId}`,
    method: 'get'
  })
}

//21 当月研发费用
export function getRdExpenseData(fihfId) {
  return request({
    url: `/financial/display/rdExpense/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 当月制造费用
export function getManufacturingExpensesMonthData(fihfId) {
  return request({
    url: `/financial/display/manufacturingExpensesMonth/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 当月原材料存货额
export function getMonthlyRawMaterialInventoryData(fihfId) {
  return request({
    url: `/financial/display/monthlyRawMaterialInventory/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 当月在制品存货额
export function getMonthlyWorkInProgressInventoryData(fihfId) {
  return request({
    url: `/financial/display/monthlyWorkInProgressInventory/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 存货增长率/销售增长率
export function getGrowthRateInventorySalesInventoryData(fihfId) {
  return request({
    url: `/financial/display/growthRateInventorySalesInventory/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 应收账款周转率
export function getTurnoverRateReceivableInventoryData(fihfId) {
  return request({
    url: `/financial/display/turnoverRateReceivableInventory/?fihfId=${fihfId}`,
    method: 'get'
  })
}

//33 当月资金周转率
export function getCapitalTurnoverRateData(fihfId) {
  return request({
    url: `/financial/display/capitalTurnoverRate/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 库存商品周转率
export function getInventoryTurnoverRateData(fihfId) {
  return request({
    url: `/financial/display/inventoryTurnoverRate/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 原材料周转率
export function getRawMaterialTurnoverRateData(fihfId) {
  return request({
    url: `/financial/display/rawMaterialTurnoverRate/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 在制品周转率
export function getInprogressTurnoverRateData(fihfId) {
  return request({
    url: `/financial/display/inprogressTurnoverRate/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 一年以上暂估行项目
export function getLongEstimatedItemsData(fihfId) {
  return request({
    url: `/financial/display/longEstimatedItems/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 当日在制品金额
export function getInprogressDayrevenueData(fihfId) {
  return request({
    url: `/financial/display/inprogressDayrevenue/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 月度存货总金额
export function getMonthlyInventoryTotalAmountData(fihfId) {
  return request({
    url: `/financial/display/monthlyInventoryTotalAmount/?fihfId=${fihfId}`,
    method: 'get'
  })
}

// 当日经济增加值
export function getAddedValueMonthlyData(fihfId) {
  return request({
    url: `/financial/display/addedValueMonthly/?fihfId=${fihfId}`,
    method: 'get'
  })
}
