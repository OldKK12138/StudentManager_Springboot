<template>
  <div>
    <!--搜索框-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <!--<el-input style="width: 200px" placeholder="请输入" suffix-icon="el-icon-message" class="ml-5"></el-input> -->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button> <!--添加重置按钮-->
    </div>
    <!--每条数据的操作标签-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-upload action="https://loaclhost:9090/student/import" :show-file-list="false" accept=".xlsx" :on-error="uploadError" :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary"  class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" class="ml-5" @click="exp">导出 <i class="el-icon-top"></i></el-button>
      <el-button type="danger" @click="delBatch">批量删除 <i class="el-icon-remove-outline"></i></el-button>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>  <!--用于批量删除-->
      <el-table-column prop="id" label="ID" width="60"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column label="操作">
        <!-- eslint-disable-next-line -->
        <!-- <template slot-scope="scope">-->
        <template #default="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除这一条数据吗？"
              @confirm="handleDelete(scope.row.id)">
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="学生添加" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="90px" size="small">
        <el-form-item label="用户名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户年龄" >
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveDialog">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Student",
  data() {
    return {
      name: "",
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      multipleSelection: [],
      dialogFormVisible: false,
      form: {},
      headerBg: 'headerBg'
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      // fetch("http://localhost:9090/student/page?pageNum=" + this.pageNum +"&pageSize=" + this.pageSize + "&name=" + this.name)
      //     .then(response => response.json())
      //     .then(response => {
      //       console.log(response)
      //       this.tableData = response.data
      //       this.total = response.total
      //     })
      this.request.get("/student/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(response =>{
        console.log(response)
        this.tableData = response.records
        this.total = response.total
      })
    },
    reset() {  // 搜索框重置
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {   // 进行页面选择的pageSize进行绑定以便进行load()
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {  // 进行页面选择的pageNum进行绑定以便进行load()
      this.pageNum = pageNum
      this.load()
    },
    handleAdd() {  // 新增按钮
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) { // 修改按钮
      this.form = row  // 将要编辑的这一行数据赋值给form，以便在dialog框中进行展示、修改
      this.dialogFormVisible = true  // dialog框显示
      // this.save()  // 进行update请求，这里我们将insert和update放到同一个函数中了
    },
    handleDelete(id) { // 删除按钮
      this.request.delete("/student/"+id)
          .then(response => {
            if(response){
              this.$message.success("删除成功")
              this.load()
            }else{
              this.$message.error(("删除失败"))
            }
          })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val  // 用于存储批量删除的数据的id（初始时并不止包括id）
    },
    delBatch(){ // 批量删除
      let ids = this.multipleSelection.map(v => v.id) // [{}, {}, {}] ==> [1, 2, 3]
      this.request.post("/student/del/batch", ids).then(response => {
        if(response){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
    },
    saveDialog(){  // 新增dialog的保存按钮
      this.request.post("/student", this.form)
          .then(response =>{
            if(response){ // 添加成功
              this.$message.success("新增成功")
              this.dialogFormVisible = false // 新增成功后关闭dialog
              this.load()
            }else{ // 添加失败
              this.$message.error("新增失败")
            }
          })
    },
    exp() {  // 文件导出
      window.open("http://localhost:9090/student/export")
    },
    handleExcelImportSuccess() {  // 文件上传成功
      this.$message.success("导入成功")
      this.load()
    },
    uploadError(){
      this.$message.error("导入失败")
    }
  }
}
</script>

<style scoped>
.headerBg{
  background-color: #ccc!important;
}
</style>