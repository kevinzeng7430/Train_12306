import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
const routes = [
    /*
    两种路由方式：
    1. 静态路由：打包的时候全部打包，所以打包的项目会比较大
    2. 动态路由：懒加载，需要用的时候再加载，打包的项目会比较小
     */
  {
    path: '/',
    redirect: '/login' // 重定向到登录页面
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    // 方法1：
    path: '/',
    name: 'home',
    component: HomeView

  },
  {
    // 方法2：懒加载
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
