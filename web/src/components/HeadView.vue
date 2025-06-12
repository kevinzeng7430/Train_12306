<template>
  <a-layout-header class="header" id="components-layout-demo-top-side-2">
    <div class="logo"/>
    <div style="float: right; color: white">
      你好：{{ member.mobile}} &nbsp;&nbsp;
      <router-link to="/login">
        <a-button type="primary" size="small" style="color: white">退出登录</a-button>
      </router-link>
    </div>
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 111</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>

</template>

<script>
import {defineComponent, ref,} from 'vue';
import store from "@/store";


export default defineComponent({
  name: "head-view",
  setup() {
    let member = store.state.member

    return {
      selectedKeys1: ref(['2']),
      member
    };
  },
});

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>