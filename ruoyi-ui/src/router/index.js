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
        path: 'indicators1',
        component: () => import('@/views/financial/display/indexChart/indicators1'),
        name: 'Indicators1',
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
        path: 'indicators2',
        component: () => import('@/views/financial/display/indexChart/indicators2'),
        name: 'Indicators2',
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
        path: 'indicators3',
        component: () => import('@/views/financial/display/indexChart/indicators3'),
        name: 'Indicators3',
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
        path: 'indicators4',
        component: () => import('@/views/financial/display/indexChart/indicators4'),
        name: 'Indicators4',
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
        path: 'indicators5',
        component: () => import('@/views/financial/display/indexChart/indicators5'),
        name: 'Indicators5',
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
        path: 'indicators6',
        component: () => import('@/views/financial/display/indexChart/indicators6'),
        name: 'Indicators6',
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
        path: 'indicators7',
        component: () => import('@/views/financial/display/indexChart/indicators7'),
        name: 'Indicators7',
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
        path: 'indicators8',
        component: () => import('@/views/financial/display/indexChart/indicators8'),
        name: 'Indicators8',
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
        path: 'indicators9',
        component: () => import('@/views/financial/display/indexChart/indicators9'),
        name: 'Indicators9',
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
        path: 'indicators10',
        component: () => import('@/views/financial/display/indexChart/indicators10'),
        name: 'Indicators10',
        meta: { title: '指标10：净利润' }
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
        path: 'indicators11',
        component: () => import('@/views/market/display/indexChart/indicators11'),
        name: 'Indicators11',
        meta: { title: '指标11：各销售网点月度(I类)电车盘锦区域内订单占比' }
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
        path: 'indicators12',
        component: () => import('@/views/market/display/indexChart/indicators12'),
        name: 'Indicators12',
        meta: { title: '指标12：各销售网点月度(IV-V类)内燃车盘锦区域内订单占比' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators13',
        component: () => import('@/views/market/display/indexChart/indicators13'),
        name: 'Indicators13',
        meta: { title: '指标13：各主要车型产量月度比率' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators14',
        component: () => import('@/views/market/display/indexChart/indicators14'),
        name: 'Indicators14',
        meta: { title: '指标14：各主要车型同比增长比例' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators15',
        component: () => import('@/views/market/display/indexChart/indicators15'),
        name: 'Indicators15',
        meta: { title: '指标15：各销售网点月度各主要车型分布表' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators16',
        component: () => import('@/views/market/display/indexChart/indicators16'),
        name: 'Indicators16',
        meta: { title: '指标16：各网点已接单未发车总台数' }
      }
    ]
  }, {
    path: '/market',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators17',
        component: () => import('@/views/market/display/indexChart/indicators17'),
        name: 'Indicators17',
        meta: { title: '指标17：各网点未排产订单台数' }
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
        path: 'indicators18',
        component: () => import('@/views/market/display/indexChart/indicators18'),
        name: 'Indicators18',
        meta: { title: '指标18：各网点已完工未发车订单数' }
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
        path: 'indicators19',
        component: () => import('@/views/market/display/indexChart/indicators19'),
        name: 'Indicators19',
        meta: { title: '指标19：各网点已到期未完工订单数' }
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
        path: 'indicators20',
        component: () => import('@/views/financial/display/indexChart/indicators20'),
        name: 'Indicators20',
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
        path: 'indicators21',
        component: () => import('@/views/financial/display/indexChart/indicators21'),
        name: 'Indicators21',
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
        path: 'indicators22',
        component: () => import('@/views/financial/display/indexChart/indicators22'),
        name: 'Indicators22',
        meta: { title: '指标22：当月制造费用' }
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
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators26',
        component: () => import('@/views/financial/display/indexChart/indicators26'),
        name: 'Indicators26',
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
        path: 'indicators27',
        component: () => import('@/views/financial/display/indexChart/indicators27'),
        name: 'Indicators27',
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
        path: 'indicators28',
        component: () => import('@/views/financial/display/indexChart/indicators28'),
        name: 'Indicators28',
        meta: { title: '指标28：当月在制品存货额' }
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
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators30',
        component: () => import('@/views/financial/display/indexChart/indicators30'),
        name: 'Indicators30',
        meta: { title: '指标30：存货增长率/销售增长率' }
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
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators32',
        component: () => import('@/views/financial/display/indexChart/indicators32'),
        name: 'Indicators32',
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
        path: 'indicators33',
        component: () => import('@/views/financial/display/indexChart/indicators33'),
        name: 'Indicators33',
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
        path: 'indicators34',
        component: () => import('@/views/financial/display/indexChart/indicators34'),
        name: 'Indicators34',
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
        path: 'indicators35',
        component: () => import('@/views/financial/display/indexChart/indicators35'),
        name: 'Indicators35',
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
        path: 'indicators36',
        component: () => import('@/views/financial/display/indexChart/indicators36'),
        name: 'Indicators36',
        meta: { title: '指标36：在制品周转率' }
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
        path: 'indicators49',
        component: () => import('@/views/enterprise/display/indexChart/indicators49'),
        name: 'Indicators49',
        meta: { title: '指标49：一线从业人数' }
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
    path: '/enterprise',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators51',
        component: () => import('@/views/enterprise/display/indexChart/indicators51'),
        name: 'Indicators51',
        meta: { title: '指标51：公司平均从业人数（月度）' }
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
        path: 'indicators52',
        component: () => import('@/views/enterprise/display/indexChart/indicators52'),
        name: 'Indicators52',
        meta: { title: '指标52：工资总额年度占比' }
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
        path: 'indicators53',
        component: () => import('@/views/enterprise/display/indexChart/indicators53'),
        name: 'Indicators53',
        meta: { title: '指标53：日清日结考核得分）' }
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
        path: 'indicators54',
        component: () => import('@/views/enterprise/display/indexChart/indicators54'),
        name: 'Indicators54',
        meta: { title: '指标54：累计人均收入' }
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
        path: 'indicators55',
        component: () => import('@/views/enterprise/display/indexChart/indicators55'),
        name: 'Indicators55',
        meta: { title: '指标55：月度累计生产人均收入' }
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
        path: 'indicators56',
        component: () => import('@/views/enterprise/display/indexChart/indicators56'),
        name: 'Indicators56',
        meta: { title: '指标56：月度累计职能人均收入' }
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
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators66',
        component: () => import('@/views/financial/display/indexChart/indicators66'),
        name: 'Indicators66',
        meta: { title: '指标66：一年以上暂估行项目' }
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
    path: '/financial',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators70',
        component: () => import('@/views/financial/display/indexChart/indicators70'),
        name: 'Indicators70',
        meta: { title: '指标70：当日在制品金额' }
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
        name: 'Indicators47',
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
  },
  {
    path: '/enterprise',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'indicators76',
        component: () => import('@/views/enterprise/display/indexChart/indicators76'),
        name: 'Indicators76',
        meta: { title: '指标76：职能部门人均加班费用' }
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
        path: 'indicators77',
        component: () => import('@/views/financial/display/indexChart/indicators77'),
        name: 'Indicators77',
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
        path: 'indicators78',
        component: () => import('@/views/financial/display/indexChart/indicators78'),
        name: 'Indicators78',
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
  //文件模块
  // { 
  //   path: '/file/filemanagement/index',
  //   component: Layout,
  //   hidden: true,
  //   permissions: ['file:filemanagement:list'],
  //   children: [
  //     {
  //       path: '/file/filemanagement/historyVersions/:regulationsId(\\d+)', // 路由路径
  //       component: () => import('@/views/file/filemanagement/historyVersions'), // 对应的组件
  //       name: 'historyVersions', // 路由名称
  //       meta: { title: '历史版本管理' } // 元信息，用于设置标题等
  //     }
  //   ]
  // },

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
