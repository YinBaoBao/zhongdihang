import Vue from 'vue';
import Router from 'vue-router';
import login from '@/components/login';
import index from '@/components/index/index';
import Xq from '@/components/houseinfo/xq';

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
      component: index
    },
    {
      path: '/',
      redirect: 'xq'
    },
    {
      path: '/xq',
      component: Xq
    }
  ]
});
