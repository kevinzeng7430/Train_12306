<template>
  <a-layout-header class="header" id="components-layout-demo-top-side-2">
    <div class="logo"/>
    <div style="float: right; color: white">
      你好：{{ member.mobile}} &nbsp;&nbsp;
      <router-link to="/login">
        <a-button type="primary" size="smaller" style="color: white">退出登录</a-button>
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys = "selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/>&nbsp; 欢迎页
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <coffee-outlined/>&nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>

</template>

<script>
import {defineComponent, ref, watch,} from 'vue';
import store from "@/store";
import router from "@/router";


export default defineComponent({
  name:  "head-view", //name: "head-view" 用于指定该 Vue 组件的名称。
  setup() {
    let member = store.state.member;
    const selectedKeys = ref([]); // 选中的菜单项
    watch(() => router.currentRoute.value.path, (newValue)=>{
      console.log('watch newValue', newValue);
      selectedKeys.value = [];
      selectedKeys.value.push(newValue);
    }),{ immediate: true, deep: true };
    return {
      selectedKeys,
      member
    };
  },
});

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>