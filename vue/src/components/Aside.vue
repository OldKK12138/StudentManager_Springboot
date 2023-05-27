<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x:hidden;"
           background-color="rgb(48, 65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router>  <!--若路由不生效，则需要再加上 :default-active="this.$router.path" -->
    <!-- 增加一个标题栏目 -->
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-menu-item index="/">
      <i class="el-icon-house"></i>
      <span>主页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>系统管理</span>
      </template>
      <el-menu-item index="/student">
        <i class="el-icon-s-custom"></i>
        <span>用户管理</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
}
</script>

<style scoped>

</style>