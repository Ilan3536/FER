import { createApp } from 'vue';
import store from './store/index'
import App from './App.vue';
import router from './router';
import axios from './util/axiosConfig'

const app =  createApp(App)

app.use(router)
app.use(store)
app.config.globalProperties.$axios = axios

app.mount('#app');
