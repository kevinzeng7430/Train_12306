SessionStorage = {
    get: function (key){
        const v = sessionStorage.getItem(key);
        //还有一个localStorage都h5原生的方法
        // 判断 v 是否存在，且不是 undefined 字符串
        if(v && typeof (v) !== 'undefined' && v !== 'undefined') {
            try {
                // 尝试将 v 作为 JSON 字符串解析为对象
                return JSON.parse(v);
            } catch (e) {
                // 如果解析失败（不是 JSON 格式），直接返回原始值
                return v;
            }
        }
    },
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data));
        //sessionStorage.setItem(key, ...)：
        // 以指定的 key 作为键，把数据保存到 sessionStorage，页面关闭后数据会被清除。
    },
    remove: function (key) {
        sessionStorage.removeItem(key);
    },
    clear: function () {
        sessionStorage.clear();
    }
}