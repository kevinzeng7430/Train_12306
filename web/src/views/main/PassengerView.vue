<template>
  <div>
    <h1>乘车人管理</h1>
    <a-button type="primary" @click="showModal">新增</a-button>
    <a-modal v-model:visible="visible" title="新增乘车人" @ok="handleOk"
          ok-text="确定" cancel-text="取消">
      <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{span: 20}">
          <a-form-item label="姓名">
            <a-input v-model:value="passenger.name" placeholder="请输入姓名"/>
          </a-form-item>
          <a-form-item label="身份证号">
            <a-input v-model:value="passenger.idCard" placeholder="请输入身份证号"/>
          </a-form-item>
        <a-form-item label="类型">
          <a-select v-model:value="passenger.type" placeholder="请选择类型">
            <a-select-option value="1">成人</a-select-option>
            <a-select-option value="2">儿童</a-select-option>
            <a-select-option value="3">学生</a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script >
import {defineComponent, reactive, ref} from 'vue';
import axios from "axios";
import {notification} from "ant-design-vue";
export default defineComponent({
  setup() {
    const visible = ref(false);
    const passenger = reactive({
      id:undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined, // 1:成人, 2:儿童, 3:学生
      createTime: undefined,
      updateTime: undefined,
    });
    const showModal = () => {
      visible.value = true;
    };
    const handleOk = e => {
      console.log(e);
      axios.post("member/passenger/save", passenger).then(response => {
        console.log(response);
        let data = response.data;
        if (data.success) {
          notification.success({message:"操作成功", description: "乘车人信息已保存"});
          visible.value = false;
          // visible.value = false; // 关闭模态框
          // passenger.id = undefined; // 清空乘车人信息
          // passenger.memberId = undefined;
          // passenger.name = undefined;
          // passenger.idCard = undefined;
          // passenger.type = undefined;
          // passenger.createTime = undefined;
          // passenger.updateTime = undefined;
          // 可以在这里添加成功提示或刷新列表等操作
        } else {
          // 处理错误情况
          notification.error({message:"请求失败", description: data.message});
          console.error(data.message);
        }
      }).catch(error => {
        console.error("请求失败", error);
      });
    };
    return {
      passenger,
      visible,
      showModal,
      handleOk,
    };
  },
});
</script>

<style>

</style>