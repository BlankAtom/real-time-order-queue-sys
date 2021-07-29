<template>
  <el-card shadow="never" class="index">
    <template #header>
      <div class="card_header">
        <b>菜系列表</b>
      </div>
    </template>
<!--        <div  style="padding-left: 40%">-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item align="left">
        <el-button type="primary" @click="showCreateDialog">新建菜系</el-button>
      </el-form-item>
      <el-form-item  style="padding-left: 40%">
        <el-input v-model="searchName" placeholder="请输入菜系名称" clearable @clear="clearCurPage"  prefix-icon="el-icon-search"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="clearCurPage">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
        :data="cuisines"
        style="width: 100%">

      <el-table-column
          label="菜系代码"
          prop="code">
      </el-table-column>
      <el-table-column
          label="菜系名称"
          prop="name">
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" type="primary" @click="showUpdateDialog(scope.$index,scope.row)">修改信息</el-button>
          <el-button size="mini" type="danger" @click="deleteCuisine(scope.$index,scope.row)">删除账号</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div  style="padding-top: 20px " align="center"  >
      <el-pagination
          layout="prev, pager, next"
          page-size="9"
          hide-on-single-page="true"
          background
          :total="total"
          @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </el-card>
  <el-dialog
      title="新建菜系"
      v-model="isShowCreateCuisineDialog"
      width="25%"
      :before-close="handleClose">
    <el-form :model="newCuisine" :label-position="labelPosition" label-width="80px" status-icon :rules="rules">
      <el-form-item label="菜系名称">
        <el-input v-model="newCuisine.name" placeholder="请输入菜系名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitCreate">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <el-dialog
      title="修改菜系"
      v-model="isShowUpdateCuisineDialog"
      width="25%"
      :before-close="handleClose">
    <el-form :model="newCuisine" :label-position="labelPosition" label-width="80px" status-icon :rules="rules">
      <el-form-item label="菜系名称">
        <el-input v-model="newCuisine.name" placeholder="请输入菜系名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitUpdate">提交</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import {ElMessage} from "element-plus";
export default {
  data(){
    return{
      labelPosition: 'right',
      isShowCreateCuisineDialog: false,
      isShowUpdateCuisineDialog: false,
      searchName: '',
      curPage:1,
      pageCount:9,
      total:9,
      cuisines:[],
      newCuisine:{
        name:'',
        code:''
      }
    }
    rules:{

    }
  },
  created() {
    this.clearCurPage()
  },
  methods: {
    clearCurPage(){
      let params = {curPage:this.curPage,pageCount:9,searchName:this.searchName}
      if(this.searchName===''){
        //获取所有数据的长度
        this.$axios
            .post('/cuisine/findAllCuisine')
            .then((response)=>{
              // console.log(response.data.length)
              this.total = response.data.length
            }).catch((error)=>{
              console.log(error)
            })
        //获取当前页的数据
        this.$axios
            .post('/cuisine/findPageCuisine',this.$qs.stringify(params))
            .then((response)=>{
              // console.log(response.data)
              this.cuisines = response.data
            })
      }else{
        //获取模糊查询的数据长度
        this.$axios
            .post('/cuisine/findAllCuisineByName',this.$qs.stringify(params))
            .then((response)=>{
              this.total = response.data.length
            }).catch((error)=>{
          console.log(error)
        })
        //获取模糊查询当前页的数据
        this.$axios
            .post('/cuisine/findPageCuisineByName',this.$qs.stringify(params))
            .then((response)=>{
              this.cuisines = response.data
            })
      }
    },
    /**
     * 处理换页时的数据显示
     * @param val 页数
     */
    handleCurrentChange(val){
      this.curPage = val
      this.clearCurPage()
    },
    showUpdateDialog(index,row){
      this.newCuisine.name = row.name
      this.newCuisine.code = row.code
      this.isShowUpdateCuisineDialog = true
    },
    showCreateDialog(){
      this.newCuisine.name = ''
      this.isShowCreateCuisineDialog = true
    },
    handleClose(){
      this.clearCurPage()
      this.isShowUpdateCuisineDialog = false
      this.isShowCreateCuisineDialog =false
    },
    submitCreate(){
      let params = {cname:this.newCuisine.name}
      this.$axios
          .post('/cuisine/createCuisine',this.$qs.stringify(params))
          .then((response)=>{
            if(response.data===1){
              ElMessage.success({
                message: '添加成功',
                type: 'success'
              });
            }
          }).catch((error)=>{
            console.log(error)
      })
      this.clearCurPage()
      this.isShowCreateCuisineDialog = false
    },
    submitUpdate(){
      let params = {code:this.newCuisine.code,cname:this.newCuisine.name}
      this.$axios
          .post('/cuisine/updateCuisine',this.$qs.stringify(params))
          .then((response)=>{
            if(response.data===1){
              ElMessage.success({
                message: '添加成功',
                type: 'success'
              });
            }
          }).catch((error)=>{
        console.log(error)
      })
      this.clearCurPage()
      this.isShowUpdateCuisineDialog = false
    },
    deleteCuisine(index,row){
      // console.log(row.code)
      let params = {code: row.code}
      this.$axios
          .post('/cuisine/deleteCuisineByCode',this.$qs.stringify(params))
          .then((response)=>{
            if(response.data===1){
              ElMessage.success({
                message: '删除成功',
                type: 'success'
              });
            }
          })
      this.clearCurPage()
    }
  }
}
</script>

<style scoped>

</style>