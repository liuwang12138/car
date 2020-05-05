import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
      {
          path: '/',
          redirect: '/loadCurrent'
      },
      {
          path: '/',
          component: resolve => require(['../components/Home.vue'], resolve),
          meta: { title: '' },
          children:[
              {
                  path: '/loadCurrent',
                  component: resolve => require(['../page/loadCurrent.vue'], resolve),
                  meta: { title: '装载当前配置' }
              },
              {
                  path: '/user',
                  component: resolve => require(['../page/userPrivilege/user.vue'], resolve),
                  meta: { title: '用户配置' }
              },
              {
                  path: '/group',
                  component: resolve => require(['../page/userPrivilege/group.vue'], resolve),
                  meta: { title: '组配置' }
              },
              {
                  path: '/userAndGroup',
                  component: resolve => require(['../page/userPrivilege/userAndGroup.vue'], resolve),
                  meta: { title: '用户与用户组配置' }
              },
              {
                  path: '/business',
                  component: resolve => require(['../page/business/business.vue'], resolve),
                  meta: { title: '业务配置' }
              },
              {
                  path: '/bGroup',
                  component: resolve => require(['../page/business/bGroup.vue'], resolve),
                  meta: { title: '组配置' }
              },
              {
                  path: '/bAndGroup',
                  component: resolve => require(['../page/business/bAndGroup.vue'], resolve),
                  meta: { title: '业务与业务组配置' }    
              },
              {
                  path: '/port',
                  component: resolve => require(['../page/portInfo/port.vue'], resolve),
                  meta: { title: '接口配置' }   
              },
              {
                  path: '/system',
                  component: resolve => require(['../page/portInfo/system.vue'], resolve),
                  meta: { title: '系统参数配置' }
              },
              {
                  path: '/table',
                  component: resolve => require(['../page/portInfo/table.vue'], resolve),
                  meta: { title: '表配置' }
              },
              {
                  path: '/move',
                  component: resolve => require(['../page/portInfo/move.vue'], resolve),
                  meta: { title: '迁移配置' }
              },
              {
                  path: '/userBusGroup',
                  component: resolve => require(['../page/userBusGroup.vue'], resolve),
                  meta: { title: '用户业务组关系配置' }
              },
              {
                  path: '/codeImport',
                  component: resolve => require(['../page/codeImport.vue'], resolve),
                  meta: { title: '脚本导出' }
              },
              {
                  path: '/message',
                  component: resolve => require(['../page/message.vue'], resolve),
                  meta: { title: '短信统计' }
              }
          ]
      },
      {
          path: '/login',
          component: () => import(/* webpackChunkName: "login" */ '../page/login.vue'),
          meta: { title: '登录' }
      },
  ]
})