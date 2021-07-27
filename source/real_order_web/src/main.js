import { createApp } from "vue";
import "./assets/css/reset.css";
// import installElementPlus from "./utils/e_ui";
import ElementPlus from "element-plus";
import "element-plus/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "./utils/axios";
import qs from "qs";
import oss from "./utils/oss"; /* 上传文件 */
// import VueSession from 'vue-session'
const app = createApp(App);

app.config.globalProperties.$axios = axios;
app.config.globalProperties.$qs = qs;
app.config.globalProperties.$oss = oss;
// app.config.globalProperties.$session = VueSession;
// 按需加载
// installElementPlus(app);
// 全部加载
app.use(ElementPlus);

app.use(router).use(store).mount("#app");
