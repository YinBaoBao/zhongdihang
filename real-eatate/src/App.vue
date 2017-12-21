<template>
  <div id="app">
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
  export default {
    name: 'app'
  };
</script>

<style>
  #app {
    height: 100%;
  }

  .el-dropdown-menu .imgs{
    width: 16px;
    height: 16px;
    padding-right: 6px;
    vertical-align: middle;
  }
  .el-dropdown-menu .text{
    vertical-align: middle;
  }

</style>
