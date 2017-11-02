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
        show: false
      };
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
