const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    open:{
        target: ' http://localhost:9000/login', // 打开浏览器时访问的地址
    }
  }
})
