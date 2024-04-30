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
export function getTotalSalesRevenueData(data) {
  return request({
    url: `/financial/display/totalSalesRevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 集团外部销售收入
export function getExternalGroupSalesRevenueData(data) {
  return request({
    url: `/financial/display/externalGroupSalesRevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 整车产量
export function getTotalVehicleProductionData(data) {
  return request({
    url: `/financial/display/totalVehicleProduction`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 整车销量
export function getTotalVehicleSalesData(data) {
  return request({
    url: `/financial/display/totalVehicleSales`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 新产品销售收入
export function getNewProductSalesRevenueData(data) {
  return request({
    url: `/financial/display/newProductSalesRevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 特色产品收入
export function getSpecialtyProductRevenueData(data) {
  return request({
    url: `/financial/display/specialtyProductRevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 主营业务成本
export function getCOGSData(data) {
  return request({
    url: `/financial/display/COGS`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 整机销售成本
export function getTotalSalesCostData(data) {
  return request({
    url: `/financial/display/totalSalesCost`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 净利润
export function getNetProfitData(data) {
  return request({
    url: `/financial/display/netProfit`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

//20当月管理费用
export function getManagementExpenseData(data) {
  return request({
    url: `/financial/display/managementExpense`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

//21 当月研发费用
export function getRdExpenseData(data) {
  return request({
    url: `/financial/display/rdExpense`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月制造费用
export function getManufacturingExpensesMonthData(data) {
  return request({
    url: `/financial/display/manufacturingExpensesMonth`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月原材料存货额
export function getMonthlyRawMaterialInventoryData(data) {
  return request({
    url: `/financial/display/monthlyRawMaterialInventory`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月在制品存货额
export function getMonthlyWorkInProgressInventoryData(data) {
  return request({
    url: `/financial/display/monthlyWorkInProgressInventory`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月库存商品存货额
export function getMonthAmountInStockData(data) {
  return request({
    url: `/financial/display/monthAmountInStock`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 存货增长率/销售增长率
export function getGrowthRateInventorySalesData(data) {
  return request({
    url: `/financial/display/growthRateInventorySales`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 应收账款周转率
export function getTurnoverRateReceivableData(data) {
  return request({
    url: `/financial/display/turnoverRateReceivable`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

//33 当月资金周转率
export function getCapitalTurnoverRateData(data) {
  return request({
    url: `/financial/display/capitalTurnoverRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 库存商品周转率
export function getInventoryTurnoverRateData(data) {
  return request({
    url: `/financial/display/inventoryTurnoverRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 原材料周转率
export function getRawMaterialTurnoverRateData(data) {
  return request({
    url: `/financial/display/rawMaterialTurnoverRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 在制品周转率
export function getInprogressTurnoverRateData(data) {
  return request({
    url: `/financial/display/inprogressTurnoverRate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 一年以上暂估行项目
export function getLongEstimatedItemsData(data) {
  return request({
    url: `/financial/display/longEstimatedItems`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当日在制品金额
export function getInprogressDayrevenueData(data) {
  return request({
    url: `/financial/display/inprogressDayrevenue`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 月度存货总金额
export function getMonthlyInventoryTotalAmountData(data) {
  return request({
    url: `/financial/display/monthlyInventoryTotalAmount`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 当月经济增加值
export function getAddedValueMonthlyData(data) {
  return request({
    url: `/financial/display/addedValueMonthly`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}
