import Vue from 'vue'
import Router from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Home from '@/pages/Home'
import List from '@/pages/List'
import Maps from '@/pages/Maps'
import Login from '@/pages/Login'
import Detail from '@/pages/Detail'
import Mine from '@/pages/Mine'

Vue.use(Router)
Vue.use(ElementUI)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/Login'
    },
    {
      path: '/Home',
      component: Home
    },
    {
        path: '/List',
        component: List
    },
    {
        path: '/Maps',
        component: Maps
    },
    {
        path: '/Detail/:id',
        name: 'Detail',
        component: Detail
    },
    {
        path: '/Mine',
        component: Mine
    },
    {
        path: '/Login',
        component: Login
    }
  ]
})
