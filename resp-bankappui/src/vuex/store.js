/**
 * Created by YINBAOBAO on 2017/9/19.
 */
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    api: window.__congig.dev_serve,
    application: '',
    proposer: '',
    value: ''
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
    }
  }
});

export default store;
