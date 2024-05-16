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
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators30',
        component: () => import('@/views/financial/financialDisplay/indexChart/indicators30'),
        name: 'Indicators30',
        meta: { title: '指标数据' }
      },

      {
        path: 'indicators70',
        component: () => import('@/views/financial/financialDisplay/indexChart/indicators70'),
        name: 'Indicators70',
        meta: { title: '指标数据' }
      },

      {
        path: 'index-detail-financial',
        component: () => import('@/views/financial/financialDisplay/indexDetailFinancial'),
        name: 'IndexDetailFinancial',
        meta: {
          title: '指标详情',
        }
      }
    ]
  },

  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators23',
        component: () => import('@/views/safety/display/indexChart/indicators23'),
        name: 'Indicators23',
        meta: { title: '指标23：当月设备维修总费用' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators24',
        component: () => import('@/views/production/display/indexChart/indicators24'),
        name: 'Indicators24',
        meta: { title: '指标24：当月单台非BOM物料费用' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators25',
        component: () => import('@/views/production/display/indexChart/indicators25'),
        name: 'Indicators25',
        meta: { title: '指标25：当月单台低值易耗费用' }
      }
    ]
  },

  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators29',
        component: () => import('@/views/production/display/indexChart/indicators29'),
        name: 'Indicators29',
        meta: { title: '指标29：在制物资年化周转天数' }
      }
    ]
  },

  {
    path: '/supply',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators31',
        component: () => import('@/views/supply/display/indexChart/indicators31'),
        name: 'Indicators31',
        meta: { title: '指标31：集团管控物资采购金额占比' }
      }
    ]
  },

  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators37',
        component: () => import('@/views/production/display/indexChart/indicators37'),
        name: 'Indicators37',
        meta: { title: '指标37：人均生产台数' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators38',
        component: () => import('@/views/production/display/indexChart/indicators38'),
        name: 'Indicators38',
        meta: { title: '指标38：人均产值' }
      }
    ]
  },
  {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators39',
        component: () => import('@/views/market/display/indexChart/indicators39'),
        name: 'Indicators39',
        meta: { title: '指标39：商品车计划兑现率' }
      }
    ]
  },
  {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators40',
        component: () => import('@/views/market/display/indexChart/indicators40'),
        name: 'Indicators40',
        meta: { title: '指标40：订单兑现率' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators41',
        component: () => import('@/views/production/display/indexChart/indicators41'),
        name: 'Indicators41',
        meta: { title: '指标41：上线及时率' }
      }
    ]
  },
  {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators42',
        component: () => import('@/views/market/display/indexChart/indicators42'),
        name: 'Indicators42',
        meta: { title: '指标42：大叉位叉车整机交货天数平均数' }
      }
    ]
  },
  {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators43',
        component: () => import('@/views/market/display/indexChart/indicators43'),
        name: 'Indicators43',
        meta: { title: '指标43：电动车整机交货天数' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators44',
        component: () => import('@/views/market/display/indexChart/indicators44'),
        name: 'Indicators44',
        meta: { title: '指标44：小吨位内燃叉车整机交货天数' }
      }
    ]
  },
  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators45',
        component: () => import('@/views/safety/display/indexChart/indicators45'),
        name: 'Indicators45',
        meta: { title: '指标45：重点设备故障率' }
      }
    ]
  },
  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators46',
        component: () => import('@/views/safety/display/indexChart/indicators46'),
        name: 'Indicators46',
        meta: { title: '指标46：当月设备故障累计停产时间' }
      }
    ]
  },
  {
    path: '/tech',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators47',
        component: () => import('@/views/tech/display/indexChart/indicators47'),
        name: 'Indicators47',
        meta: { title: '指标47：非标订单平均技术准备天数' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators48',
        component: () => import('@/views/production/display/indexChart/indicators48'),
        name: 'Indicators48',
        meta: { title: '指标48：一线当月加班时长' }
      }
    ]
  },
  {
    path: '/enterprise',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [

      {
        path: 'index-detail-enterprise',
        component: () => import('@/views/enterprise/display/indexDetailEnterprise'),
        name: 'IndexDetailEnterprise',
        meta: { title: '指标数据' }
      }

    ]
  },
  {
    path: '/partybuilding',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators50',
        component: () => import('@/views/partybuilding/display/indexChart/indicators50'),
        name: 'Indicators50',
        meta: { title: '指标50：党建月度考核排名' }
      }
    ]
  },


  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators57',
        component: () => import('@/views/quality/display/indexChart/indicators57'),
        name: 'Indicators57',
        meta: { title: '指标57：三包期内新车返修率' }
      }
    ]
  },
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators58',
        component: () => import('@/views/quality/display/indexChart/indicators58'),
        name: 'Indicators58',
        meta: { title: '指标58：三包期内整车月度返修率' }
      }
    ]
  },
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators59',
        component: () => import('@/views/quality/display/indexChart/indicators59'),
        name: 'Indicators59',
        meta: { title: '指标59：外部质量损失率' }
      }
    ]
  },
  // 指标60：被删除
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators61',
        component: () => import('@/views/quality/display/indexChart/indicators61'),
        name: 'Indicators61',
        meta: { title: '指标61：月度售后质量问题总数' }
      }
    ]
  },
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators62',
        component: () => import('@/views/quality/display/indexChart/indicators62'),
        name: 'Indicators62',
        meta: { title: '指标62：售后问题生产责任次数' }
      }
    ]
  },
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators63',
        component: () => import('@/views/quality/display/indexChart/indicators63'),
        name: 'Indicators63',
        meta: { title: '指标63：质量考核季度排名' }
      }
    ]
  },
  {
    path: '/quality',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators64',
        component: () => import('@/views/quality/display/indexChart/indicators64'),
        name: 'Indicators64',
        meta: { title: '指标64：平均无故障时间' }
      }
    ]
  },

  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators67',
        component: () => import('@/views/safety/display/indexChart/indicators67'),
        name: 'Indicators67',
        meta: { title: '指标67：主要设备故障总次数' }
      }
    ]
  },
  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators68',
        component: () => import('@/views/safety/display/indexChart/indicators68'),
        name: 'Indicators68',
        meta: { title: '指标68：设备故障类别次数分布图' }
      }
    ]
  },
  {
    path: '/safety',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators69',
        component: () => import('@/views/safety/display/indexChart/indicators69'),
        name: 'Indicators69',
        meta: { title: '指标69：当月设备维修成本' }
      }
    ]
  },

  {
    path: '/tech',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators71',
        component: () => import('@/views/tech/display/indexChart/indicators71'),
        name: 'Indicators71',
        meta: { title: '指标71：研发项目计划进度完成率' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators74',
        component: () => import('@/views/production/display/indexChart/indicators74'),
        name: 'Indicators74',
        meta: { title: '指标74：日/月/年上线数' }
      }
    ]
  },
  {
    path: '/production',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators75',
        component: () => import('@/views/production/display/indexChart/indicators75'),
        name: 'Indicators75',
        meta: { title: '指标75：日/月/年完工数' }
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

  //流程模块-流程新建
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
  //流程模块-流程统计
  {
    path: '/statistics',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'statistics',
        component: () => import('@/views/process/statistics/index'),
        name: '流程',
        meta: { title: '流程统计', activeMenu: '/statistics/index' }
      }
    ]
  },

  //流程模块 -- 流程指标: 流程变更次数统计
  {
    path: '/process/statistics/index', //ori from 路径
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: '/process/statistics/indicators/:id',// to 路径
        component: () => import('@/views/process/statistics/indicators'), //组件vue路径
        name: 'indicators',
        meta: { title: '详细流程统计' }
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
  //文件模块
  {
    path: '/file/filemanagement/index',
    component: Layout,
    hidden: true,
    permissions: ['file:filemanagement:list'],
    children: [
      {
        path: '/file/filemanagement/historyVersions/:regulationsId(\\d+)', // 路由路径
        component: () => import('@/views/file/filemanagement/historyVersions'), // 对应的组件
        name: 'historyVersions', // 路由名称
        meta: { title: '历史版本管理' } // 元信息，用于设置标题等
      }
    ]
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
