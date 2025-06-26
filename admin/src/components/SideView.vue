<template>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys = "selectedKeys"
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <span>
            <user-outlined/>
            欢迎页
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/about">
      <router-link to="/about">
          <span>
            <laptop-outlined/>
            关于
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/base/station">
        <router-link to="/base/station">
          <span>
            <coffee-outlined/>&nbsp; 车站管理
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/base/train">
        <router-link to="/base/train">
          <span>
            <coffee-outlined/>&nbsp; 列车管理
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/base/train_station">
        <router-link to="/base/train_station">
          <span>
            <coffee-outlined/>&nbsp; 列车车站管理
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="train_carriage">
        <router-link to="train_carriage">
          <span>
            <coffee-outlined/>&nbsp; 列车车厢管理
          </span>
        </router-link>
      </a-menu-item>
      <a-menu-item key="/base/train_seat">
        <router-link to="/base/train_seat">
          <span>
            <coffee-outlined/>&nbsp; 列车座位管理
          </span>
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>

</template>

<script>
import {defineComponent, ref, watch,} from 'vue';
import router from "@/router";


export default defineComponent({
  name: "side-view",

  setup() {
    // ref是Vue 3中用于创建响应式数据的函数，用来定义响应式变量的
    //reactive 是Vue 3中用于创建响应式对象的函数，用来定义响应式对象的
    const selectedKeys = ref([]);
    watch( () => router.currentRoute.value.path, (newValue) => {
      console.log('watch newValue', newValue);
      selectedKeys.value = [];
      selectedKeys.value.push(newValue);
    }),{ immediate: true, deep: true };

    // 这是 watch 侦听器的配置选项：
    // immediate: true 表示在侦听器初始化时立即执行一次回调函数。
    // deep: true 表示深度侦听，被侦听的数据如果是对象或数组，内部属性变化也会触发回调

    return {
      selectedKeys,
    };
  },
});

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>