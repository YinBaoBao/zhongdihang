<template>
  <div id="Register">
    <print :Propose="Propose" :mortgage="mortgage" :bdcqk="bdcqk" :xwsx="xwsx" ref="print"></print>
    <div class="info">
      <ul>注：
        <li>1、按标准表格样式填写内容后，生成样表</li>
        <li>2、其中代理人在没有代理的情况下为空</li>
        <li>3、地役情况签章、询问事宜为空</li>
        <li>4、左上角条形码由系统生成，便于国土局线下办理接件</li>
      </ul>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import print from './print.vue';

  export default {
    name: '',
    props: {
      Propose: {
        type: Object
      },
      mortgage: {
        type: Object
      },
      bdcqk: {
        type: Object
      },
      xwsx: {
        type: Object
      }
    },
    data() {
      return {};
    },
    methods: {
      _print() {
        this.$confirm('打印后页面数据将清空,请保存申请后在打印，是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          setTimeout(_ => {
            this.$router.push({path: '/print'});
            this.$refs.print.winprint();
          }, 500);
        });
      }
    },
    created() {
    },
    components: {
      print
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  #Register
    width: 100%
    position: relative
    padding-bottom: 70px
    .info
      width: 100%
      position: absolute
      left: 0px
      bottom: 20px
      padding-left: 20PX
      background: #fff
      li
        padding: 4px 15px
</style>
