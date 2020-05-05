import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/orderRecord'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/orderRecord',
                    component: () => import(/* webpackChunkName: "orderRecord" */ '../components/page/OrderRecord.vue'),
                    meta: { title: '销售记录管理' }
                },
                {
                    // userManage
                    path: '/userManage',
                    component: () => import(/* webpackChunkName: "userManage" */ '../components/page/UserManage.vue'),
                    meta: { title: '客户管理' }
                },
                {
                    // carList
                    path: '/carList',
                    component: () => import(/* webpackChunkName: "carList" */ '../components/page/CarList.vue'),
                    meta: { title: '库存管理' }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                },
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
