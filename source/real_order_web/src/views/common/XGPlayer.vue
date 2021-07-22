
<template>
  <div class="hello">
    <h1>菜品展示</h1>
    <el-row class="table-grid-content">
      <el-col :span="18" class="grid">
        <el-input v-model="input" placeholder="请输入搜索内容"></el-input>
      </el-col>
      <el-col :span="3" class="grid" :gutter="1">
        <el-button type="success"  icon="el-icon-search">搜索</el-button>
      </el-col>
      <el-col :span="2" class="grid" :gutter="15">
        <el-button type="primary" @click="addMembers()">增加</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" :stripe="true" :border="true" width="100%">
      <el-table-column label="菜id" prop="d_id"></el-table-column>
      <el-table-column label="菜名" prop="d_name"></el-table-column>
      <el-table-column label="商家id" prop="m_id"></el-table-column>
      <el-table-column label="价格" prop="d_price"></el-table-column>
      <el-table-column label="图片" prop="d_pic"></el-table-column>
      <el-table-column label="注意事项" prop="d_note"></el-table-column>
      <el-table-column label="销量" prop="d_sum"></el-table-column>
      <el-table-column label="菜系" prop="cuision_note"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="modifyData(scope.$index, scope.row)">修改</el-button>
          <el-button type="danger" @click="deleteData(scope.$index,tableData)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog  v-model="centerDialogVisible" >
      <el-form :model="editForm">
<!--        <el-form-item label="日期" :picker-options="pickerOptions">-->
<!--          <el-date-picker v-model="editForm.date" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="菜名">
          <el-input v-model="editForm.d_name"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="editForm.d_price"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="editForm.d_pic"></el-input>
        </el-form-item>
        <el-form-item label="注意事项">
          <el-input v-model="editForm.d_note"></el-input>
        </el-form-item>
        <el-form-item label="菜系">
          <el-input v-model="editForm.cuision_code"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button @click="closeDialog()">取消</el-button>
        <el-button type="primary" @click="sumbitEditRow()">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog v-model="isAddMembers">
      <el-form :model="addForm">
<!--        <el-form-item label="日期" :picker-options="pickerOptions">-->
<!--          <el-date-picker v-model="addForm.date" type="date" placeholder="选择日期" value-format="yyyy-MM-dd"></el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="菜id">-->
<!--          <el-input v-model="addForm.d_id"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="菜名">
          <el-input v-model="addForm.d_name"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="addForm.d_price"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="addForm.d_pic"></el-input>
        </el-form-item>
        <el-form-item label="注意事项">
          <el-input v-model="addForm.d_note"></el-input>
        </el-form-item>
        <el-form-item label="菜系">
          <el-input v-model="addForm.cuision_code"></el-input>
        </el-form-item>

      </el-form>
      <div>
        <el-button @click="closeDialog()">取消</el-button>
        <el-button type="primary" @click="sumbitAddRow()">确定</el-button>
      </div>
    </el-dialog>

  </div>

  <el-pagination
      background
      layout="prev, pager, next"
      :total="100"
      @current-change="page">
  </el-pagination>


</template>

<script>
// import { musicBroadcastingDetails } from '@/api/index.js'
import axios from 'axios'
import {getCurrentInstance} from "vue";
import {getRandomInt} from "element-plus/es/utils/util";
import {ElMessage} from "element-plus";


 var _index;
 var  merchant_now='merchant_8995566'  //等待商家id传入
export default {

  data() {

    return {

      // msg: 'Welcome to Your Vue.js App',
      centerDialogVisible: false,
      isAddMembers: false,
      editForm: [],
      addForm: [],
      searchData: '',
      input: '',
      id:'',
      // pickerOptions: {
      //   disabledDate(time) {
      //     return time.getTime() > Date.now();
      //   }
      // },
      tableData: [{
        d_id: '',
        d_name: '',
        m_id: '',
        d_price: '',
        d_pic: '',
        d_note: '',
        d_sum: '',
        cuision_code:''
      }

      ],

    }
  },
  created (){

    const _this = this
    axios.get('http://localhost:8080/dish/findAll').then(function(resp){
      console.log(resp.data)
      _this.tableData = resp.data
      _this.pageSize = resp.data.size
      _this.total = resp.data.total

     // console.log("总页数"+_this.pageSize+"      总条数  "+_this.total)
    })
    // const {proxy} = getCurrentInstance();
    // proxy.$axios
    //     .post('/dish/findAll')
    //     .then(res => {
    //
    //       console.log(res)
    //     })
    //     .catch(err => {
    //       console.log("login err", err);
    //
    //     });
  },
  methods: {

    // page(currentPage){
    //   const _this = this
    //   axios.get('http://localhost:8080/dish/findAll/'+(currentPage-1)+'/6').then(function(resp){
    //     console.log(resp)
    //     _this.tableData = resp.data.content
    //     _this.pageSize = resp.data.size
    //     _this.total = resp.data.total
    //   })
    // },
    deleteData(index, row) {
      this.tableData.splice(index, 1)
      console.log("进行了删除操作")
      console.log("index的值是：" + index)
      console.log("row的值是：", row)
    },
    modifyData(index, row) {
      this.centerDialogVisible= true

      // console.log(this.centerDialogVisible)
      this.editForm = Object.assign({}, row); //重置对象
      _index = index;

      console.log("index的值：" + index)
      console.log("_index的值：" + _index)
      console.log("this.editForm：", this.editForm.d_name) //代表选择的这一行的数据
    },
    sumbitEditRow() {

      console.log("edit的值" + this.editForm.d_name)

      this.tableData[_index].d_name = this.editForm.d_name;
      this.tableData[_index].d_price = this.editForm.d_price;
      this.tableData[_index].d_pic = this.editForm.d_pic;
      this.tableData[_index].d_note = this.editForm.d_note;
      this.tableData[_index].cuision_code = this.editForm.cuision_code;
      this.centerDialogVisible = false;
     // console.log("数据：" + this.editForm.d_name)
     // console.log("对象数组", this.tableData)
    },
    closeDialog() {
      this.centerDialogVisible = false
      this.isAddMembers = false
      console.log( this.editForm)
      console.log( this.addForm)

    },
    addMembers() {
      this.isAddMembers = true
      this.addForm = {
        d_id: '',
        d_name: '',
        m_id: '',
        d_price: '',
        d_pic: '',
        d_note: '',
        d_sum: '',
        cuision_code:''
      }

      console.log(this.addForm)
    },
    sumbitAddRow() {

      const nowDate = new Date();
      var monthNow ;
      monthNow = 1+nowDate.getMonth()
      this.tableData = this.tableData || []
      // console.log("表格是:" +monthNow )
      this.addForm.d_sum=0
      this.addForm.d_id="dish_"+
          this.tableData[0].m_id.substring(this.tableData[0].m_id.length-4,this.tableData[0].m_id.length)+
          "_"+monthNow+"_"+nowDate.getDate()+"_"+getRandomInt(100)
      this.tableData.push({
        d_id: this.addForm.d_id,
        d_name: this.addForm.d_name,
        m_id: merchant_now,
        d_price: this.addForm.d_price,
        d_pic: this.addForm.d_pic,
        d_note:this.addForm.d_note,
        d_sum: this.addForm.d_sum,
        cuision_code: this.addForm.cuision_code
      })
      this.isAddMembers = false;

      //const {proxy} = getCurrentInstance();
      let params = {
        d_name:this.addForm.d_name,
        d_id: this.addForm.d_id,
        m_id:merchant_now,
        d_price: this.addForm.d_price,
        d_pic: this.addForm.d_pic,
        d_note:this.addForm.d_note,
        d_sum: this.addForm.d_sum,
        cuision_code: this.addForm.cuision_code};
     // const _this = this
      this.$axios
          .post("/dish/insertDish", this.$qs.stringify(params))
          .then(res => {
            //let {code, data, msg, success} = res.data;

            console.log("ssssuucccceeeesssss!!!!")
          })
          .catch(err => {
        console.log("///////////////////////报错", err);

      });

      //var str =JSON.stringify(this.addForm)
      // axios.put('http://localhost:8080/dish/insertDish',params).then(function(resp){
      //   console.log(resp.data)
      //   console.log(111111111111111111111111111111111111111111111111)
      // })

    }
    // searchMembers(){
    //   const input = this.input
    //   if (input) {
    //     console.log("input输入的搜索内容：" + this.input)
    //     return this.tableData.filter(data => {
    //       console.log("object:" + Object.keys(data.d_name))
    //       return Object.keys(data).some(key => {
    //         return String(data[key]).toLowerCase().indexOf(input) > -1
    //       })
    //     })
    //   }
    //   return this.tableData
    // }
  },
  computed: {
    tableData() {
      const input = this.input
      if (input) {
        console.log("input输入的搜索内容：" + this.input)
        return this.tableData.filter(data => {
          console.log("object:" + Object.keys(data))
          return Object.keys(data).some(key => {
            return String(data[key]).toLowerCase().indexOf(input) > -1
          })
        })
      }
      return this.tableData
    }
  },

}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.table-grid-content {
  border-radius: 4px;
  height: 50px;
  background: #ebeef5;
  padding: 10px;
}
</style>
