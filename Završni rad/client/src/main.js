import { createApp } from 'vue';
import store from './store/index'
import App from './App.vue';
import router from './router';
import axios from './util/axiosConfig'
import { formatTime, formatYear } from './util/filters'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/dist/vuetify.min.css';
import 'vuetify/dist/vuetify.css';

//Vue data table
import Vue3EasyDataTable from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

const vuetify = createVuetify({
    components,
    directives,
  })

const app = createApp(App)

app.config.globalProperties.$formatTime = formatTime
app.config.globalProperties.$formatYear = formatYear

app.component('EasyDataTable', Vue3EasyDataTable);
app.use(vuetify)
app.use(router)
app.use(store)
app.config.globalProperties.$axios = axios

app.mount('#app');
