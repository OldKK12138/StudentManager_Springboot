<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1; font-size: 30px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>

<!--    &lt;!&ndash;页签&ndash;&gt;-->
<!--      <el-breadcrumb separator="/" style="display: flex; margin-left: 10px;">-->
<!--        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
<!--        <el-breadcrumb-item>用户管理</el-breadcrumb-item>-->
<!--      </el-breadcrumb>-->

    <el-dropdown style="width: 80px; cursor: pointer">
      <span>王小虎</span> <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <!-- <i class="el-icon-setting" style="margin-right: 15px"></i> -->
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>
          <router-link to="/login" style="text-decoration: none">退出</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String
  },
  methods: {
    collapse() {
      this.$emit("asideCollapse")
    }
  }
}
</script>

<style scoped>

</style>