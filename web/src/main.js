import { createApp } from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'
import Antd, {notification} from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";

const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

const icons = Icons;
for(const i in icons) {
  app.component(i, icons[i]);
}

/*添加axios拦截器*/
axios.interceptors.request.use(function (config) {
  console.log('请求参数',config);
  const _token = store.state.member.token;
  if(_token){
    config.headers.token = _token; // 设置请求头
    console.log("设置请求头token", _token);
  }
  return config;
},error => {
  return Promise.reject(error);
});


axios.interceptors.response.use(function (response) {
  console.log('响应数据',response);
  return response;
},error => {
  const response = error.response;
  const status = response.status;
  if(status === 401){
    console.log('未登录或登录过期，请重新登录');
    store.commit("setMember",{}); // 清除登录状态
    console.log('触发 notification');
    router.push('/'); // 跳转到登录页面
    notification.error({
      message: '未登录或登录过期',
      description: '请重新登录',
    });
    // 返回一个带有标准结构的响应对象
    // return Promise.resolve({
    //   data: {
    //     success: false,
    //     //这个message会传递给loginView
    //     message: '未登录或登录过期，请重新登录123',
    //     content: null
    //   }
    // });
    //使用空 Promise 阻止消息传递：
    return new Promise(()=>{});
  }
  // 其他错误继续抛出
  return Promise.reject(error);
});

// 设置axios的基础URL
axios.defaults.baseURL = process.env.VUE_APP_BASE_SERVER_URL
console.log('环境', process.env.NODE_ENV);
console.log('服务端', process.env.VUE_APP_BASE_SERVER_URL);