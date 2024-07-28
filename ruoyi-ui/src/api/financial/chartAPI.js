import request from '@/utils/request'

const api = {

  // 主营业务收入
  getMainRevenueData(data) {
    return request({
      url: `/financial/display/mainRevenue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 整机销售
  getTotalSalesRevenueData(data) {
    return request({
      url: `/financial/display/totalSalesRevenue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 集团外部销售收入
  getExternalGroupSalesRevenueData(data) {
    return request({
      url: `/financial/display/externalGroupSalesRevenue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 整车产量
  getTotalVehicleProductionData(data) {
    return request({
      url: `/financial/display/totalVehicleProduction`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 整车销量
  getTotalVehicleSalesData(data) {
    return request({
      url: `/financial/display/totalVehicleSales`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 新产品销售收入
  getNewProductSalesRevenueData(data) {
    return request({
      url: `/financial/display/newProductSalesRevenue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 特色产品收入
  getSpecialtyProductRevenueData(data) {
    return request({
      url: `/financial/display/specialtyProductRevenue`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 主营业务成本
  getCOGSData(data) {
    return request({
      url: `/financial/display/COGS`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 整机销售成本
  getTotalSalesCostData(data) {
    return request({
      url: `/financial/display/totalSalesCost`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 净利润
  getNetProfitData(data) {
    return request({
      url: `/financial/display/netProfit`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  //20当月管理费用
  getManagementExpenseData(data) {
    return request({
      url: `/financial/display/managementExpense`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  //21 当月研发费用
  getRdExpenseData(data) {
    return request({
      url: `/financial/display/rdExpense`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月制造费用
  getManufacturingExpensesMonthData(data) {
    return request({
      url: `/financial/display/manufacturingExpensesMonth`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月原材料存货额
  getMonthlyRawMaterialInventoryData(data) {
    return request({
      url: `/financial/display/monthlyRawMaterialInventory`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月在制品存货额
  getMonthlyWorkInProgressInventoryData(data) {
    return request({
      url: `/financial/display/monthlyWorkInProgressInventory`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月库存商品存货额
  getMonthAmountInStockData(data) {
    return request({
      url: `/financial/display/monthAmountInStock`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },


  // 存货增长率/销售增长率
  getGrowthRateInventoryAndSalesData(data) {
    return request({
      url: `/financial/display/growthRateInventoryAndSales`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 应收账款周转率
  getTurnoverRateReceivableData(data) {
    return request({
      url: `/financial/display/turnoverRateReceivable`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  //33 当月资金周转率
  getCapitalTurnoverRateData(data) {
    return request({
      url: `/financial/display/capitalTurnoverRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 库存商品周转率
  getInventoryTurnoverRateData(data) {
    return request({
      url: `/financial/display/inventoryTurnoverRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 原材料周转率
  getRawMaterialTurnoverRateData(data) {
    return request({
      url: `/financial/display/rawMaterialTurnoverRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 在制品周转率
  getInprogressTurnoverRateData(data) {
    return request({
      url: `/financial/display/inprogressTurnoverRate`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 一年以上暂估行项目
  getLongEstimatedItemsData(data) {
    return request({
      url: `/financial/display/longEstimatedItems`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },



  // 月度存货总金额
  getMonthlyInventoryTotalAmountData(data) {
    return request({
      url: `/financial/display/monthlyInventoryTotalAmount`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  },

  // 当月经济增加值
  getAddedValueMonthlyData(data) {
    return request({
      url: `/financial/display/addedValueMonthly`,
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime
      }
    })
  }
}

export default api




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


// 存货增长率/销售增长率
export function getGrowthRateInventorySalesData(data) {
  return request({
    url: `/financial/display/growthRateInventoryAndSales`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 财务指标资产负债总计
export function getBalanceSumData(data) {
  return request({
    url: `/financial/data/balance/sum`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 月度数据求和展示
export function getMonthSumData(data) {
  return request({
    url: `/financial/data/fill/sum`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 利润表求和展示
export function getInterestSumData(data) {
  return request({
    url: `/financial/data/interests/sum`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

// 月度数据比率展示展示
export function getMonthRateData(data) {
  return request({
    url: `/financial/data/fill/rate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}


// 资产负债表比率展示
export function getBalanceData(data) {
  return request({
    url: `/financial/data/balance/rate`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime
    }
  })
}

