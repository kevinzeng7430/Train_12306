import { createRouter, createWebHistory } from 'vue-router'
import LoginView from "@/views/LoginView.vue";
import {notification} from "ant-design-vue";
import store from "@/store";
const routes = [
    /*
    两种路由方式：
    1. 静态路由：打包的时候全部打包，所以打包的项目会比较大
    2. 动态路由：懒加载，需要用的时候再加载，打包的项目会比较小
     */

  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/',
    name: 'main',
    component: () => import('../views/MainView.vue'),
    meta:{
        requiresAuth: true // 标记需要登录才能访问
    }
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由守卫：在路由跳转之前进行判断
router.beforeEach((to, from, next)=>{
  //要不要对meta.requiresAuth进行判断
  //// 检查目标路由是否需要认证
  if(to.matched.some(function (item){
    console.log(item,"是否需要登录校验:",item.meta.requiresAuth || false);
    return item.meta.requiresAuth;
  })) {
    const _member = store.state.member;
    console.log("页面登录校验开始:", _member);
    if (!_member || !_member.token) {
      // 如果没有登录，跳转到登录页面
      console.log("未登录，跳转到登录页面");
      notification.error({
        message: '未登录或登录过期',
        description: '请重新登录',
      });
      next({name: 'login'});// 重定向到登录页
    } else {
      next();// 已登录，允许访问
    }
  }else{
    // 不需要认证的路由直接放行
    next();
  }
});

export default router

// {
//   // 方法1：
//   path: '/',
//       name: 'home',
//     component: HomeView
//
// },
// {
//   // 方法2：懒加载
//   path: '/about',
//       name: 'about',
//     // route level code-splitting
//     // this generates a separate chunk (about.[hash].js) for this route
//     // which is lazy-loaded when the route is visited.
//     component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
// },