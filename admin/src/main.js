import { createApp } from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";
import './assets/JS/enums';

const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

const icons = Icons;
for(const i in icons) {
  app.component(i, icons[i]);
}

/*添加axios拦截器*/
axios.interceptors.request.use(function (config) {
  console.log('请求参数',config);
  return config;
},error => {
  return Promise.reject(error);
});


axios.interceptors.response.use(function (response) {
  console.log('响应数据',response);
  return response;
},error => {
  // 其他错误继续抛出
  return Promise.reject(error);
});

// 设置axios的基础URL
axios.defaults.baseURL = process.env.VUE_APP_BASE_SERVER_URL
console.log('环境', process.env.NODE_ENV);
console.log('服务端', process.env.VUE_APP_BASE_SERVER_URL);