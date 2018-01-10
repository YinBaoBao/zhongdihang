<template>
  <div class="home">
    <p class="username">{{username}},欢迎回来!</p>
    <p class="text">苏州不动产数据集成服务管理后台</p>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        username: '李延亮'
      };
    },
    created() {
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .home
    width: 100%
    height: 100%
    background: url("./wellcom.png") 0 0 no-repeat
    background-size: 100% 100%
    text-align: center
    .username, .text
      font-size: 24px
      font-family: '微软雅黑'
      color: #97a8bb
    .username
      padding-top: 260px
    .text
      padding-top: 36px
</style>
