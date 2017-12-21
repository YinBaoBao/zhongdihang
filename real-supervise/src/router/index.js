import Vue from 'vue';
import Router from 'vue-router';
import login from '@/components/login';
import index from '@/components/index/index';
import Log from '@/components/log/log';
import Statistics from '@/components/statistics/statistics';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: 'login'
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/',
      redirect: 'index'
    },
    {
      path: '/index',
      component: index,
      children: [
        {
          path: 'log',
          component: Log
        },
        {
          path: 'statistics',
          component: Statistics
        }
      ]
    }
  ]
});
