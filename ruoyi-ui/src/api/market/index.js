import request from '@/utils/request'


export function getIndex11(data) {
    return request({
      url: '/market/Index/IClassProportion',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  
  export function getIndex12(data) {
    return request({
      url: '/market/Index/IVVClassProportion',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex13(data) {
    return request({
      url: '/market/Index/ModelMonthlyProduction',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex14(data) {
    return request({
      url: '/market/Index/ModelGrowthProportion',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex15(data) {
    return request({
      url: '/market/Index/ModelBranchProportion',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex16(data) {
    return request({
      url: '/market/Index/OrderWaitSend',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex17(data) {
    return request({
      url: '/market/Index/BranchWaitProduction',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex18(data) {
    return request({
      url: '/market/Index/OverWorkWaitDelivery',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex19(data) {
    return request({
      url: '/market/Index/OvertimedOrderNumber',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex39(data) {
    return request({
      url: '/market/Index/CommercialVehicleRate',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex40(data) {
    return request({
      url: '/market/Index/OrderAchieveRate',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex42(data) {
    return request({
      url: '/market/Index/AvergaeDay',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex43(data) {
    return request({
      url: '/market/Index/ElCarAvergaeDay',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex45(data) {
    return request({
      url: '/market/Index/ElCarAvergaeDay45',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex46(data) {
    return request({
      url: '/market/Index/ElCarAvergaeDay46',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex47(data) {
    return request({
      url: '/market/Index/ElCarAvergaeDay47',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex48(data) {
    return request({
      url: '/market/Index/ElCarAvergaeDay48',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  
  export function getIndex44(data) {
    return request({
      url: '/market/Index/LitterTAvergaeDay',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex72A(data) {
    return request({
      url: '/market/Index/OrderReceiveNumber',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex72B(data) {
    return request({
      url: '/market/Index/OrderReceiveNumberB',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex72C(data) {
    return request({
      url: '/market/Index/OrderReceiveNumberC',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex73A(data) {
    return request({
      url: '/market/Index/OrderDeliveryNumber',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

  export function getIndex73B(data) {
    return request({
      url: '/market/Index/OrderDeliveryNumberB',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }
  export function getIndex73C(data) {
    return request({
      url: '/market/Index/OrderDeliveryNumberC',
      method: 'post',
      data: {
        startTime: data.startTime,
        endTime: data.endTime,
        numberInput:data.numberInput
      }
    })
  }

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
export function getMainRevenueData(fihfId) {
  return request({
    url: '/financial/display/mainRevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}


// 整机销售
export function getTotalSalesRevenueData(fihfId) {
  return request({
    url: '/financial/display/totalSalesRevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 集团外部销售收入
export function getExternalGroupSalesRevenueData(fihfId) {
  return request({
    url: '/financial/display/externalGroupSalesRevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}


// 整车产量
export function getTotalVehicleProductionData(fihfId) {
  return request({
    url: '/financial/display/totalVehicleProduction/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 整车销量
export function getTotalVehicleSalesData(fihfId) {
  return request({
    url: '/financial/display/totalVehicleSales/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 新产品销售收入
export function getNewProductSalesRevenueData(fihfId) {
  return request({
    url: '/financial/display/newProductSalesRevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 特色产品收入
export function getSpecialtyProductRevenueData(fihfId) {
  return request({
    url: '/financial/display/specialtyProductRevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 主营业务成本
export function getCOGSData(fihfId) {
  return request({
    url: '/financial/display/COGS/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 整机销售成本
export function getTotalSalesCostData(fihfId) {
  return request({
    url: '/financial/display/totalSalesCost/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 净利润
export function getNetProfitData(fihfId) {
  return request({
    url: '/financial/display/netProfit/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

//20当月管理费用 
export function getManagementExpenseMonthData(fihfId) {
  return request({
    url: '/financial/display/managementExpense/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

//21 当月研发费用
export function getRdExpenseData(fihfId) {
  return request({
    url: '/financial/display/rdExpense/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 当月制造费用
export function getManufacturingExpensesMonthData(fihfId) {
  return request({
    url: '/financial/display/manufacturingExpensesMonth/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 当月原材料存货额
export function getMonthlyRawMaterialInventoryData(fihfId) {
  return request({
    url: '/financial/display/monthlyRawMaterialInventory/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 当月在制品存货额
export function getMonthlyWorkInProgressInventoryData(fihfId) {
  return request({
    url: '/financial/display/monthlyWorkInProgressInventory/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 存货增长率/销售增长率
export function getGrowthRateInventorySalesInventoryData(fihfId) {
  return request({
    url: '/financial/display/growthRateInventorySales/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 应收账款周转率
export function getTurnoverRateReceivableInventoryData(fihfId) {
  return request({
    url: '/financial/display/turnoverRateReceivable/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

//33 当月资金周转率
export function getCapitalTurnoverRateData(fihfId) {
  return request({
    url: '/financial/display/capitalTurnoverRate/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 库存商品周转率
export function getInventoryTurnoverRateData(fihfId) {
  return request({
    url: '/financial/display/inventoryTurnoverRate/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 原材料周转率
export function getRawMaterialTurnoverRateData(fihfId) {
  return request({
    url: '/financial/display/rawMaterialTurnoverRate/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 在制品周转率
export function getInprogressTurnoverRateData(fihfId) {
  return request({
    url: '/financial/display/inprogressTurnoverRate/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 一年以上暂估行项目
export function getLongEstimatedItemsData(fihfId) {
  return request({
    url: '/financial/display/longEstimatedItems/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 当日在制品金额
export function getInprogressDayrevenueData(fihfId) {
  return request({
    url: '/financial/display/inprogressDayrevenue/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 月度存货总金额
export function getMonthlyInventoryTotalAmountData(fihfId) {
  return request({
    url: '/financial/display/monthlyInventoryTotalAmount/2024?fihfId=' + fihfId,
    method: 'get'
  })
}

// 当日经济增加值
export function getAddedValueMonthlyData(fihfId) {
  return request({
    url: '/financial/display/addedValueMonthly/2024?fihfId=' + fihfId,
    method: 'get'
  })
}