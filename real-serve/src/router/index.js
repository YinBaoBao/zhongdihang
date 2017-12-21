import Vue from 'vue';
import Router from 'vue-router';
import login from '@/components/login';
import index from '@/components/index/index';
import Houseinfo from '@/components/houseinfo/houseinfo';
import Housenum from '@/components/housenum/housenum';

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
          path: 'houseinfo',
          component: Houseinfo
        },
        {
          path: 'housenum',
          component: Housenum
        }
      ]
    }
  ]
});
