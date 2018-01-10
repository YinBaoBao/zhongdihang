/* eslint-disable semi */
/**
 * Created by YINBAOBAO on 2017/9/19.
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    departmanage: '',
    departTitle: '新增用户',
    useredit: '',
    userTitle: '新增用户'
  },
  mutations: {
    departmanage(state, events) {
      state.departmanage = events
    },
    departTitle(state, events) {
      state.departTitle = events
    },
    useredit(state, events) {
      state.useredit = events
    },
    userTitle(state, events) {
      state.userTitle = events
    }
  }
})
export default store
