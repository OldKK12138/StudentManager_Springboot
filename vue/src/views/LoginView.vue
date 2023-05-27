<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px">
            <b>登 录</b>
      </div>
      <el-form :model="student" :rules="rules" ref="studentForm">
        <el-form-item prop="name">
          <el-input size="medium"style="margin: 10px 0" prefix-icon="el-icon-user" v-model="student.name"></el-input>
        </el-form-item>
        <el-form-item prop="age">
          <el-input size="medium"style="margin: 10px 0" show-password prefix-icon="el-icon-lock" v-model="student.age"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: center">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登 录</el-button>
          <el-button type="primary" size="small" autocomplete="off">注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      student: {},
      rules: {
        name: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 10, message: '长度在2到10个字符', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在3到10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login() {
      // 先进行校验，校验成功则发送请求，否则不发送请求
      this.$refs['studentForm'].validate((valid) => {
        if(valid) { // 校验成功，则发送请求
          this.request.post("/student/login", this.student).then( res => {
            if(!res){
              this.$message.error("用户名或密码错误")
            } else{
              this.$router.push("/")
            }
          })
        } else {
          return false;
        }
      })
    }
  }
}
</script>

<style scoped>
  .wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
  }
</style>