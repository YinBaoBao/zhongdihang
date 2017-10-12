/**
 * Created by YINBAOBAO on 2017/9/19.
 */
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    api: window.__config.dev_serve,
    Host: window.__config.dev_local,
    Test: window.__config.dev_test,
    application: '',
    proposer: '',
    value: '',
    token: '',
    username: ''
  },
  getters: {
    value: state => state.value
  },
  mutations: {
    application(state, elements) {
      state.application = elements;
    },
    proposer(state, elements) {
      state.proposer = elements;
    },
    newtoken(state, element) {
      state.token = element;
    },
    newname(state, element) {
      state.username = element;
    }
  }
});

export default store;
