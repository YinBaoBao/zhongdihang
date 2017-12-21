import Vue from 'vue';
import Router from 'vue-router';
import login from '@/components/login';
import Index from '@/components/index/index';
import Home from '@/components/home/home';
import User from '@/components/User/User';
import Adduser from '@/components/User/adduser';
import Role from '@/components/role/role';
import AddRole from '@/components/role/addrole';
import Gongneng from '@/components/gongneng/gongneng';
import Addgn from '@/components/gongneng/addgn';
import Cxyj from '@/components/gongneng/cxyj';
import Content from '@/components/gongneng/content';
import Qymanage from '@/components/gongneng/qymanage';
import Department from '@/components/department/department';
import Departmanage from '@/components/department/departmanage';
import Mima from '@/components/mima/mima';
import Mishi from '@/components/mishi/mishi';
import Mishimanage from '@/components/mishi/mishimanage';

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
      component: Index,
      children: [
        {
          path: 'home',
          component: Home
        },
        {
          path: 'user',
          component: User
        },
        {
          path: 'adduser',
          component: Adduser
        },
        {
          path: 'role',
          component: Role
        },
        {
          path: 'addrole',
          component: AddRole
        },
        {
          path: 'gongneng',
          component: Gongneng
        },
        {
          path: 'addgn',
          component: Addgn,
          children: [
            {
              path: 'cxyj',
              component: Cxyj
            },
            {
              path: 'content',
              component: Content
            }
          ]
        },
        {
          path: 'qymanage',
          component: Qymanage
        },
        {
          path: 'department',
          component: Department
        },
        {
          path: 'departmanage',
          component: Departmanage
        },
        {
          path: 'mima',
          component: Mima
        },
        {
          path: 'mishi',
          component: Mishi
        },
        {
          path: 'mishimanage',
          component: Mishimanage
        }
      ]
    }
  ]
});
