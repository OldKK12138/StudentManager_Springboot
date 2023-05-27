<template>
  <el-container style="min-height: 100vh">
      <!-- <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%"> -->
      <el-aside width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
      </el-aside>

    <!--
      isCollapse：控制是左边是否收缩，类型为Boolean；logoTextShow：控制后台关系系统 这几个字是否隐藏，类型为Boolean
      collapseBtnClass：用于显示图标，类型为String；collapse()：为事件绑定，用于收缩和展开Aside
    -->

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass.sync="collapseBtnClass" @asideCollapse="collapse"/>
      </el-header>
      
      <el-main>
        <router-view />
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
// import request from "@/utils/request";  //改为通过 this.request 来使用全局注册好的request

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  components: {
    Aside,
    Header
  },
  data() {
      return {
        collapseBtnClass: 'el-icon-s-fold', 
        isCollapse: false,
        sideWidth: 200,
        logoTextShow: true,
      }
    },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods:{
    collapse() { // 点击按钮时触发
      // true时收缩，false展开
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 63 // 收缩时，宽度变为63px
        this.collapseBtnClass = 'el-icon-s-unfold'  // 收缩时，其图标变为展开
        this.logoTextShow = false // 收缩时，“后台管理系统”这些文字隐藏
      }else{
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-unfold'// 展开时，其图标变为收缩
        this.logoTextShow = true
      }
    }
  }
}
</script>
<style>

</style>