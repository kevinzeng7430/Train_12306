 <template>
  <a-layout-header class="header">
    <div class="logo">
      <router-link to="/welcome">
        12306售票系统
      </router-link>
    </div>
    <div style="float: right; color: red">
     欢迎使用控台
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
      <a-menu-item key="/about">
        <router-link to="/about">
          <coffee-outlined/>&nbsp; 关于
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>

</template>

<script>
import {defineComponent, ref, watch,} from 'vue';
import router from "@/router";


export default defineComponent({
  name:  "head-view", //name: "head-view" 用于指定该 Vue 组件的名称。
  setup() {
    const selectedKeys = ref([]); // 选中的菜单项
    watch(() => router.currentRoute.value.path, (newValue)=>{
      console.log('watch newValue', newValue);
      selectedKeys.value = [];
      selectedKeys.value.push(newValue);
    }),{ immediate: true, deep: true };
    return {
      selectedKeys,
    };
  },
});

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.logo {
  float: left;
  width: 120px;
  height: 31px;
  color: white;

}

</style>