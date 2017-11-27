<template>
  <div style="height: 100%;">
    <transition name="fade">
      <div v-if="show" class="shouye">
        <span class="text">欢迎使用不动产数据共享金融专用平台</span>
      </div>
    </transition>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        show: false,
        Applyselect: {
          getdyfs: '',
          getfwyt: '',
          gettdyt: '',
          getqlxz: '',
          getssqx1: '',
          getdjlx: ''
        }
      };
    },
    methods: {
      getdyfs() {    // 获取抵押方式
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10006
        }).then((response) => {
          response = response.body;
          if (response.body === null || response.body === '') {
            return false;
          }
          if (response.status === '200') {
            let data = response.body;
            let getdyfs = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              getdyfs.push(json);
            }
            this.Applyselect.getdyfs = getdyfs;
          }
        });
      },
      getfwyt() {    // 获取房屋用途下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 117
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let getfwyt = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              getfwyt.push(json);
            }
            this.Applyselect.getfwyt = getfwyt;
          }
        });
      },
      gettdyt() {    // 获取土地用途下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10008
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let gettdyt = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              gettdyt.push(json);
            }
            this.Applyselect.gettdyt = gettdyt;
          }
        });
      },
      getqlxz() {    // 获取权利性质下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 109
        }).then((response) => {
          response = response.body;
          if (response.body === null || response.body === '') {
            return false;
          }
          if (response.status === '200') {
            let data = response.body;
            let getqlxz = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              getqlxz.push(json);
            }
            this.Applyselect.getqlxz = getqlxz;
          }
        });
      },
      getssqx() {    // 获取所属区县下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '10002',
          pcode: ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let getssqx1 = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              getssqx1.push(json);
            }
            this.Applyselect.getssqx1 = getssqx1;
          }
        });
      },
      getdjlx() {    // 获取登记类型下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: '121',
          pcode: ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let getdjlx = [];
            for (var i = 0; i < data.length; i++) {
              if (data[i].name === '抵押登记') {
                let json = {
                  value: data[i].name,
                  code: data[i].code
                };
                getdjlx.push(json);
              }
            }
            this.Applyselect.getdjlx = getdjlx;
          }
        });
      }
    },
    created() {
    },
    activated() {
      this.show = true;
      this.$store.commit('application', '');
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .shouye
    width: 100%
    height: 100%
    background: url('./bg.png') 0 0 no-repeat
    background-size: 100%
    text-align: center
    &.fade-enter-active, &.fade-leave-active
      transition: all 0.8s linear
    &.fade-enter, &.fade-leave-active
      opacity: 0
    .text
      line-height: 700px
      font-family: '微软雅黑'
      font-size: 24px
      color: rgba(75, 75, 75, 0.6)
</style>
