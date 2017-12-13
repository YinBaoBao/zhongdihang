// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import store from './vuex/store';
import VueResource from 'vue-resource';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-default/index.css';
import './common/stylus/index.styl';
import 'jquery';
import 'babel-polyfill';

Vue.use(ElementUI);

Vue.use(VueResource); // 全局安装路由功能

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app');

Vue.http.interceptors.push((request, next) => {
  let headertoken = localStorage.getItem('headertoken');
  let username = localStorage.getItem('username');
  if (headertoken) {
    request.headers.set('token', headertoken);
    request.headers.set('username', username);
    next(response => {
      return response;
    });
  } else {
    next(response => {
      return response;
    });
  }
});

