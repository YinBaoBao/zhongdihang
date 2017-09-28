// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import '../static/config';
import router from './router';
import store from './vuex/store';
import VueResource from 'vue-resource';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';
import './common/stylus/index.styl';
import 'jquery';
import '../static/jqprint.js';
Vue.use(ElementUI);

Vue.use(VueResource); // 全局安装路由功能

// Vue.http.interceptors.push((request, next) => {
//   if (store.state.token) {
//     request.headers.set('Authorization', store.state.token);
//     next(response => {
//       return response;
//     });
//   } else {
//     next(response => {
//       return response;
//     });
//   }
// });

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app');
