<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app'
  };
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #app
    height: 100%

  .el-popover
    .el-table th, .el-table td
      height: 36px

  .el-tooltip__popper.is-dark
    background: #148583
  .el-tooltip__popper.popper__arrow
    border-bottom-color: #148583
</style>
