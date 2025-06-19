<!--vue 文件主要由 templa，script，style 三部分组成-->

<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">
        <rocket-two-tone/>
        &nbsp; 仿12306售票系统
      </h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
      >
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号' }]"
        >
          <a-input v-model:value="loginForm.mobile" aria-placeholder="手机号"/>
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '验证码' }]"
        >
          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">发送验证码</a>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>

        <!--    <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">-->
        <!--      <a-checkbox v-model:checked="loginForm.remember">Remember me</a-checkbox>-->
        <!--    </a-form-item>-->

      </a-form>
    </a-col>
  </a-row>
</template>
<script>
import {defineComponent, reactive} from 'vue';
import axios from 'axios';
import {notification} from "ant-design-vue";
import {useRouter} from "vue-router";
import store from "@/store";

export default defineComponent({
  // name: "login-view",
  setup() {
    const router = useRouter();
    const loginForm = reactive({
      mobile: '13399292922',
      code: '',
    });
    const sendCode = () => {
      axios.post("/member/member/send-code", {
        mobile: loginForm.mobile
      }).then(response => {
        console.log(response);
        let data = response.data;
        if (data.success) {
          notification.success({description: "验证码已发送，请注意查收"});
          loginForm.code = "8888";
        } else {
          notification.error({description: data.message});
        }
      });
    };

    const login = () => {
      axios.post("/member/member/login", loginForm).then(response => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "登录成功"});
          // 登录成功跳转控制台
          router.push("/main");
          store.commit("setMember", data.content);
        } else {
          notification.error({description: data.message});
        }
      });
    };
    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>
<!--CSS样式-->
<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}

.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}

</style>

