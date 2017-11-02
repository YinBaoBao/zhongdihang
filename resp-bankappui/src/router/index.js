import Vue from 'vue';
import Router from 'vue-router';

import index from '../components/index.vue';
import login from '../components/login.vue';
import application from '../components/application/application.vue';
import cancel from '../components/cancel/cancel.vue';
import search from '../components/search/search.vue';
import account from '../components/account/account.vue';
import Print from '../components/dialog/print.vue';
import Manage from '../components/manage/manage.vue';
import Shouye from '../components/shouye/shouye.vue';

Vue.use(Router);
const routes = [
  {
    path: '/',
    redirect: 'login'
  }, {
    path: '/login',
    component: login
  }, {
    path: '/',
    redirect: '/index'
  }, {
    path: '/index',
    component: index,
    children: [
      {
        path: 'application',
        component: application
      }, {
        path: 'cancel',
        component: cancel
      }, {
        path: 'search',
        component: search
      }, {
        path: 'account',
        component: account
      }, {
        path: 'manage',
        component: Manage
      },
      {
        path: 'shouye',
        component: Shouye
      }
    ]
  },
  {
    path: '/',
    redirect: 'print'
  }, {
    path: '/print',
    component: Print
  }
];
const router = new Router({
  routes
});

export default router;
