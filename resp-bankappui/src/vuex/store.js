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
    Propose: {
      qlrdlr: {
        qlrdlrdh: '',
        qlrdlrdz: '',
        qlrdlrmc: '',
        qlrdlrzjh: '',
        qlrdlrzjzl: '',
        qlrdlrzjzlmc: ''
      },
      qlrs: [
        {
          bjbh: '',
          qlrdh: '',
          qlrdz: '',
          qlrlx: '',
          qlrlxmc: '',
          qlrmc: '',
          qlrunid: '',
          qlrxh: '',
          qlrzjh: '',
          qlrzjzl: '',
          qlrzjzlmc: '',
          qlrzl: '',
          qlrzlmc: ''
        }
      ],
      ywrdlr: {
        ywrdlrdh: '',
        ywrdlrdz: '',
        ywrdlrmc: '',
        ywrdlrzjh: '',
        ywrdlrzjzl: '',
        ywrdlrzjzlmc: ''
      },
      ywrs: [
        {
          ywrunid: '',
          bjbh: '',
          ywrxh: '',
          ywrzl: '',
          ywrzlmc: '',
          ywrlx: '',
          ywrlxmc: '',
          ywrmc: '',
          ywrzjzl: '',
          ywrzjzlmc: '',
          ywrzjh: '',
          ywrdh: '',
          ywrdz: ''
        }
      ],
      bjbh: '201708200000001'
    },
    mortgage: {
      bdbzqse: '',
      bdcpgjg: '',
      dbfw: '',
      dkywbh: '',
      dkzh: '',
      dyfs: '',
      dyfsmc: '',
      dyhtqdrq: '',
      fcdymj: '',
      fcpgjg: '',
      tddymj: '',
      tdpgjg: '',
      yhdyywh: '',
      zjjzwdyfw: '',
      zqr: '',
      zwlxjssj: '',
      zwlxqssj: '',
      zwr: ''
    },
    bdcqk: {
      bdcdyh: '',
      bdcqzshx: '',
      bdcqzshy: '',
      fwmj: '',
      fwyt: '',
      fwytmc: '',
      qlxz: '',
      qlxzmc: '',
      ssqx: '',
      ssqxmc: '',
      tdmj: '',
      tdsyjssj: '',
      tdsyqssj: '',
      tdyt: '',
      tdytmc: '',
      zl: ''
    },
    xwsx: {
      afgyfeqk: '',
      gyfs: '',
      gyfsmc: '',
      qtsm: '',
      sffbcz: false,
      sffbczmc: '',
      sfgy: true,
      sfgymc: ''
    },
    upload: '',
    value: '',
    token: '',
    username: '',
    Bjbh: '201708001000001',
    Bankinfo: {
      id: '',
      bankName: '中国农业银行苏州分行',
      telephone: '0512-68268178',
      zjlxmc: '320500000010314',
      zjlx: '营业执照',
      bankAddress: ''
    }
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
    },
    newBjbh(state, element) {
      state.Bjbh = element;
    },
    newBank(state, element) {
      state.Bankinfo = element;
    },
    newPropose(state, element) {
      state.Propose = element;
    },
    newmortgage(state, element) {
      state.mortgage = element;
    },
    newbdcqk(state, element) {
      state.bdcqk = element;
    },
    newxwsx(state, element) {
      state.xwsx = element;
    }
  }
});

export default store;
