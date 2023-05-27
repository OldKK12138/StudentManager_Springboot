import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from "@/views/LoginView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'manager',
    component: () => import('../views/Manager.vue'), // 父路由
    redirect: '/home',
    children: [  // 子路由
      { path: 'home', name:'Home', component: () => import('../views/Home.vue') },  // 主页路由
      { path: 'student', name: 'Student', component: () => import('../views/StudentView.vue') } // 数据展示路由
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {  // 登录路由
    path: '/login',
    name: 'Login',
    component: () => import('../views/LoginView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
