import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * roles: ['admin', 'common']       // 访问路由的角色权限
 * permissions: ['a:a:a', 'b:b:b']  // 访问路由的菜单权限
 * meta : {
    noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */



// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
 
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
  {
    path: '/financial',
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'mainRevenue',
        component: () => import('@/views/financial/display/indexChart/mainRevenue'),
        name: 'MainRevenue',
        meta: { title: '指标1：主营业务收入' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'totalSalesRevenue',
        component: () => import('@/views/financial/display/indexChart/totalSalesRevenue'),
        name: 'TotalSalesRevenue',
        meta: { title: '指标2：整机销售收入' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'externalGroupSalesRevenue',
        component: () => import('@/views/financial/display/indexChart/externalGroupSalesRevenue'),
        name: 'ExternalGroupSalesRevenue',
        meta: { title: '指标3：集团外部销售收入' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'totalVehicleProduction',
        component: () => import('@/views/financial/display/indexChart/totalVehicleProduction'),
        name: 'TotalVehicleProduction',
        meta: { title: '指标4：整车产量' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'totalVehicleSales',
        component: () => import('@/views/financial/display/indexChart/totalVehicleSales'),
        name: 'TotalVehicleSales',
        meta: { title: '指标5：整车销量' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'newProductSalesRevenue',
        component: () => import('@/views/financial/display/indexChart/newProductSalesRevenue'),
        name: 'NewProductSalesRevenue',
        meta: { title: '指标6：新产品销售收入' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'specialtyProductRevenue',
        component: () => import('@/views/financial/display/indexChart/specialtyProductRevenue'),
        name: 'SpecialtyProductRevenue',
        meta: { title: '指标7：特色产品收入' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'COGS',
        component: () => import('@/views/financial/display/indexChart/COGS'),
        name: 'COGS',
        meta: { title: '指标8：主营业务成本' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'totalSalesCost',
        component: () => import('@/views/financial/display/indexChart/totalSalesCost'),
        name: 'TotalSalesCost',
        meta: { title: '指标9：整机销售成本' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'netProfit',
        component: () => import('@/views/financial/display/indexChart/netProfit'),
        name: 'NetProfit',
        meta: { title: '指标10：净利润' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'managementExpense',
        component: () => import('@/views/financial/display/indexChart/managementExpense'),
        name: 'ManagementExpense',
        meta: { title: '指标20：当月管理费用' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'rdExpense',
        component: () => import('@/views/financial/display/indexChart/rdExpense'),
        name: 'RdExpense',
        meta: { title: '指标21：当月研发费用' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'manufacturingExpensesMonth',
        component: () => import('@/views/financial/display/indexChart/manufacturingExpensesMonth'),
        name: 'ManufacturingExpensesMonth',
        meta: { title: '指标22：当月制造费用' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'monthlyRawMaterialInventory',
        component: () => import('@/views/financial/display/indexChart/monthlyRawMaterialInventory'),
        name: 'MonthlyRawMaterialInventory',
        meta: { title: '指标26：当月原材料存货额' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'monthlyWorkInProgressInventory',
        component: () => import('@/views/financial/display/indexChart/monthlyWorkInProgressInventory'),
        name: 'MonthlyWorkInProgressInventory',
        meta: { title: '指标27：当月在制品存货额' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'growthRateInventorySales',
        component: () => import('@/views/financial/display/indexChart/growthRateInventorySales'),
        name: 'GrowthRateInventorySales',
        meta: { title: '指标30：存货增长率/销售增长率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'turnoverRateReceivable',
        component: () => import('@/views/financial/display/indexChart/turnoverRateReceivable'),
        name: 'TurnoverRateReceivable',
        meta: { title: '指标32：应收账款周转率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'capitalTurnoverRate',
        component: () => import('@/views/financial/display/indexChart/capitalTurnoverRate'),
        name: 'CapitalTurnoverRate',
        meta: { title: '指标33：资金周转率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'inventoryTurnoverRate',
        component: () => import('@/views/financial/display/indexChart/inventoryTurnoverRate'),
        name: 'InventoryTurnoverRate',
        meta: { title: '指标34：库存商品周转率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'rawMaterialTurnoverRate',
        component: () => import('@/views/financial/display/indexChart/rawMaterialTurnoverRate'),
        name: 'RawMaterialTurnoverRate',
        meta: { title: '指标35：原材料周转率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'inprogressTurnoverRate',
        component: () => import('@/views/financial/display/indexChart/inprogressTurnoverRate'),
        name: 'InprogressTurnoverRate',
        meta: { title: '指标36：在制品周转率' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'longEstimatedItems',
        component: () => import('@/views/financial/display/indexChart/longEstimatedItems'),
        name: 'LongEstimatedItems',
        meta: { title: '指标66：一年以上暂估行项目' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'inprogressDayrevenue',
        component: () => import('@/views/financial/display/indexChart/inprogressDayrevenue'),
        name: 'InprogressDayrevenue',
        meta: { title: '指标70：当日在制品金额' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'monthlyInventoryTotalAmount',
        component: () => import('@/views/financial/display/indexChart/monthlyInventoryTotalAmount'),
        name: 'MonthlyInventoryTotalAmount',
        meta: { title: '指标77：月度存货总金额' }
      }
    ]
  },
  {
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'addedValueMonthly',
        component: () => import('@/views/financial/display/indexChart/addedValueMonthly'),
        name: 'AddedValueMonthly',
        meta: { title: '指标78：当月经济增加值' }
      }
    ]
  }
]
//
// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: () => import('@/views/system/user/authRole'),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },

  
  
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    permissions: ['system:dict:list'],
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    permissions: ['monitor:job:list'],
    children: [
      {
        path: 'index/:jobId(\\d+)',
        component: () => import('@/views/monitor/job/log'),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    permissions: ['tool:gen:edit'],
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: () => import('@/views/tool/gen/editTable'),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  },

  //流程模块
  {
    path: '/ef',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'ef',
        component: () => import('@/views/process/ef/panel'),
        name: '流程',
        meta: { title: '流程模块', activeMenu: '/process/ef' }
      }
    ]
  },
  //流程模块
  {
    path: '/project',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],

  },
  
]

// 防止连续点击多次路由报错
let routerPush = Router.prototype.push;
let routerReplace = Router.prototype.replace;
// push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}
// replace
Router.prototype.replace = function push(location) {
  return routerReplace.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
