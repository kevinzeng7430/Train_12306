import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    /*
    两种路由方式：
    1. 静态路由：打包的时候全部打包，所以打包的项目会比较大
    2. 动态路由：懒加载，需要用的时候再加载，打包的项目会比较小
     */


  {
    path: '/',
    name: 'main',
    component: () => import('../views/MainView.vue'),
    meta:{
        requiresAuth: true // 标记需要登录才能访问
    },
    children: [
      {
        path: 'welcome',
        name: 'welcome',
        component:() => import('../views/main/WelcomeView.vue'),
      },
      {
        path: 'about',
        name: 'about',
        component: () => import('../views/main/AboutView.vue'),
      },
      {
        path:'base/',
        children:[
          {
            path:'station',
            name:'station',
            component:()=> import('../views/main/base/StationView.vue'),
          },
          {
            path:'train',
            name:'train',
            component:()=> import('../views/main/base/TrainView.vue'),
          },
          {
            path:"train_station",
            name: "train_station",
            component:()=> import('../views/main/base/TrainStationView.vue'),
          },
          {
            path:"train_carriage",
            name: "train_carriage",
            component:()=> import('../views/main/base/TrainCarriageView.vue'),
          },
          {
            path:"train_seat",
            name: "train_seat",
            component:()=> import('../views/main/base/TrainSeatView.vue'),
          }
        ],
      },
      {
        path: 'batch/',
        children: [
          {
            path: 'job',
            component: () => import('../views/main/batch/JobView.vue')
          },
        ]
      },
      {
        path:'business/',
        children: [
          {
            path: 'daily-train',
            component: () => import('../views/main/business/DailyTrainView.vue'),
          },
          {
            path: 'daily-train-station',
            component: () => import('../views/main/business/DailyTrainStationView.vue'),
          },
          {
            path: 'daily-train-carriage',
            component: () => import('../views/main/business/DailyTrainCarriageView.vue'),
          },
          {
            path: 'daily-train-seat',
            component: () => import('../views/main/business/DailyTrainSeatView.vue'),
          },
          {
            path: 'daily-train-ticket',
            component: () => import('../views/main/business/DailyTrainTicketView.vue'),
          },
          {
            path: 'confirm-order',
            component: () => import('../views/main/business/ConfirmOrderView.vue'),
          }
        ],
      },
    ]
  },
  {
    path:'',
    redirect: '/welcome' // 默认重定向到登录页面
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



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