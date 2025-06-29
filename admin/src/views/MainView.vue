<template>
  <a-layout >
    <head-view></head-view>
    <a-layout>
      <side-view></side-view>
      <a-layout style="padding: 0 24px 24px">

        <a-layout-content
            :style="{ background: '#E5FFCC', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          <router-view>

          </router-view>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script>

import { defineComponent } from 'vue';
import HeadView from "@/components/HeadView.vue";
import SideView from "@/components/SideView.vue";


export default defineComponent({
  components: {
    SideView,
    HeadView,
  },
  setup() {

    return {

    };
  },
});
</script>
<style>

</style>

<!--const count = ref(0);-->
<!-- axios.get("/member/member/count").then(response => {-->
<!--   let data = response.data;-->
<!--   if (data.success) {-->
<!--     count.value = data.content;-->
<!--   } else {-->
<!--     notification.error({description: data.message});-->
<!--   }-->
<!-- });-->