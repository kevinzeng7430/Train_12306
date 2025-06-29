const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    open:{
        target: ' http://localhost:9001/', // 打开浏览器时访问的地址
    }
  }
})
