
<template>
  <div class="hello">
    <h1>菜品展示</h1>
    <el-row class="table-grid-content">
      <el-col :span="18" class="grid">
        <el-input v-model="input" placeholder="请输入搜索内容" clearable @clear="clear"></el-input>
      </el-col>
      <el-col :span="3" class="grid" :gutter="1">
        <el-button type="success" @click="searchDish()" icon="el-icon-search">搜索</el-button>
      </el-col>
      <el-col :span="3" class="grid" :gutter="10" >
        <el-button type="primary" @click="addMembers()">增加</el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" :stripe="true" :border="true" width="100%">
      <el-table-column label="菜id" prop="d_id"></el-table-column>
      <el-table-column label="菜名" prop="d_name"></el-table-column>
      <el-table-column label="商家id" prop="m_id"></el-table-column>
      <el-table-column label="价格" prop="d_price"></el-table-column>
      <el-table-column label="图片" prop="d_pic">

      <template   #default="scope">
        <img :src="scope.row.d_pic"  min-width="70" height="70" />
      </template>

      </el-table-column>
      <el-table-column label="注意事项" prop="d_note"></el-table-column>
      <el-table-column label="销量" prop="d_sum"></el-table-column>
      <el-table-column label="菜系" prop="cuision_code" :formatter="cuisionType"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="modifyData(scope.$index, scope.row)">修改</el-button>
          <el-button type="danger" @click="deleteData(scope.$index,scope.row)">删除</el-button>
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
      page-size="9"
      :total="totalPage"
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
 var total_size;
 var  merchant_now='merchant_8995566'  //等待商家id传入
export default {
  data() {
    return {
      currentPage:1,
      totalPage: 7,
      // msg: 'Welcome to Your Vue.js App',
      centerDialogVisible: false,
      isAddMembers: false,
      editForm: [],
      addForm: [],
      deleteForm:[],
      searchForm:[],
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
        cuision_code: ''
      }

      ],

    }
  },
  created (){
    let search_params = {merchant_now};
    const _this = this
    this.$axios
        .post("/dish/findByid", this.$qs.stringify(search_params))
        .then(res => {
          _this.searchForm = res.data
          total_size=res.data.length
          this.totalPage=res.data.length
         // console.log(this.totalPage+"测试/////////////////")
          // console.log("搜索 数据加载ssssuucccceeeesssss!!!!"+total_size)
        })
        .catch(err => {
          console.log("///////////////////////报错", err);

        })

    let params = {
      current_page:1,
      size_page: 9,
      m_id:merchant_now
      };
    this.$axios
        .post("/dish/findPage", this.$qs.stringify(params))
        .then(res => {

          _this.tableData = res.data
          //_this.total=total_size
         // console.log("数据加载ssssuucccceeeesssss!!!!")
        })
        .catch(err => {
          console.log("///////////////////////报错", err);

        })
    console.log(this.totalPage)
  },
  methods: {
    searchDish(){

      let params = {d_name: this.input,current_page:this.currentPage,size_page:9, m_id: merchant_now}
      //获取模糊查询所有
      this.$axios
          .post('/dish/findAllByd_name',this.$qs.stringify(params))
          .then((response)=>{
            this.total = response.data.length
          }).catch((error)=>{
        console.log(error)
      })
      //获取模糊查询按页面
      this.$axios
          .post('dish/findPageByd_name',this.$qs.stringify(params))
          .then((response)=>{
            this.tableData = response.data
          }).catch((error)=>{
        console.log(error)
      })

      console.log(this.input)
    },
    page(currentPage){
     const _this = this
      this.currentPage=currentPage
      let params = {
        current_page:currentPage,
        size_page: 9,
        m_id:merchant_now,
        d_name: this.input
      };
      if(this.input===''){
      this.$axios
          .post("/dish/findPage", this.$qs.stringify(params))
          .then(res => {

            _this.tableData = res.data
           // _this.total=total_size
           // console.log("数据加载ssssuucccceeeesssss!!!!")
          })
          .catch(err => {
            console.log("///////////////////////报错", err);

          })
      //console.log(this.totalPage)
         }
      else {
        this.$axios
            .post('/dish/findPageByd_name',this.$qs.stringify(params))
            .then((response)=>{
              this.tableData = response.data
              // this.total = this.accounts.length
            }).catch((error)=>{
          console.log(error)
        })
      }

    },
    deleteData(index, row) {
      this.deleteForm = Object.assign({}, row); //重置对象

      this.tableData.splice(index, 1)
      console.log("进行了删除操作")
     //console.log("index的值是：" + index)
      console.log("该菜品的id为：", this.deleteForm.d_id)

      var dish_id=this.deleteForm.d_id
      let params ={dish_id}
      this.$axios
          .post("/dish/deleteDish", this.$qs.stringify(params))
          .then(res => {


            // console.log("数据加载ssssuucccceeeesssss!!!!")
          })
          .catch(err => {
            console.log("///////////////////////报错", err);

          })
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
      this.editForm.d_sum=0
      let params = {
        d_name:this.editForm.d_name,
        d_id: this.editForm.d_id,
        m_id:merchant_now,
        d_price: this.editForm.d_price,
        d_pic: this.editForm.d_pic,
        d_note:this.editForm.d_note,
        d_sum: this.editForm.d_sum,
        cuision_code: this.editForm.cuision_code};
      // const _this = this
      this.$axios
          .post("/dish/updateDish", this.$qs.stringify(params))
          .then(res => {
            //let {code, data, msg, success} = res.data;

            // console.log("更新ssssuucccceeeesssss!!!!")
          })
          .catch(err => {
            console.log("更新///////////////////////报错", err);

          });
    },
    closeDialog() {
      this.centerDialogVisible = false
      this.isAddMembers = false
    //  console.log( this.editForm)
     // console.log( this.addForm)

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

    //  console.log(this.addForm)
    },
    sumbitAddRow() {

      const nowDate = new Date();
      var monthNow ;
      monthNow = 1+nowDate.getMonth()
      this.tableData = this.tableData || []
      // console.log("表格是:" +monthNow )
      this.addForm.d_sum=0
      this.addForm.d_id="dish_"+
          merchant_now.substring(merchant_now.length-4,merchant_now.length)+
          "_"+monthNow+nowDate.getDate()+"_"+getRandomInt(99)
      // this.addForm.d_id="dish_"+
      //     merchant_now.substring(merchant_now.length-4,merchant_now.length)
      //     +"_"+getRandomInt(99)
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

            // console.log("ssssuucccceeeesssss!!!!")
          })
          .catch(err => {
        console.log("///////////////////////报错", err);

      });



    },
    cuisionType:function (row,column,cellValue){
      if(cellValue==="1"){
        return "菜系1"
      }
      else if(cellValue==="2")
        return "菜系2"
      else if(cellValue==="3")
        return "菜系3"
      else if(cellValue==="4")
        return "菜系4"
      else if(cellValue==="5")
        return "菜系5"


    },
    clear(){
      //获取所有数据
      this.currentPage=1
      let params = {
        current_page:1,
        size_page: 9,
        m_id:merchant_now,
      };

      this.$axios
          .post('/dish/findAll')
          .then((response)=>{
            //获得所有页数显示的数据量
            this.total = response.data.length
          })
          .catch((error)=>{
            console.log(error)
          })


      this.$axios
          .post('/dish/findPage',this.$qs.stringify(params))
          .then((response)=>{
            this.tableData = response.data
          }).catch((error)=>{
        console.log(error)
      })
    }

  },
  // computed: {
  //   tableData() {
  //     const input = this.input
  //     if (input) {
  //       console.log("input输入的搜索内容：" + this.input)
  //       return this.searchForm.filter(data => {
  //         console.log("object:" + Object.keys(data))
  //         return Object.keys(data).some(key => {
  //           return String(data[key]).toLowerCase().indexOf(input) > -1
  //         })
  //       })
  //     }
  //     return this.tableData
  //   }
  // },

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
