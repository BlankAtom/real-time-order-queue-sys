<template>
    <el-card shadow="never" class="index">
        <template #header>
            <div class="card_header">
                <b>数据列表</b>
            </div>
        </template>
        <el-table
            :data="tableData"
            style="width: 100%">
          <el-table-column
              label="买家名称"
              prop="name">
          </el-table-column>
          <el-table-column
              label="订单价格"
              prop="date">
          </el-table-column>
          <el-table-column
              label="订单"
              prop="name">
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      <div  style="padding-top: 20px " align="center"  >
        <el-pagination
          layout="prev, pager, next"
          page-size="7"
          hide-on-single-page="true"
          background

          :total="totalPage"
          @current-change="handleCurrentChange">
      </el-pagination>
      </div>

    </el-card>
</template>

<script >
import {getCurrentInstance} from "vue";

export default {
  data(){
    return {
      totalPage: 1,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      },{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
    }
  },
  setup(){
    const { proxy} = getCurrentInstance()
    proxy.$axios
        .get('/data/findOrders?m_id=merchant_8995566')
        .then((response)=>{
          console.log(response)
        }).catch((error)=>{
          console.log(error)
        })
  },
  created() {
    const thisView = this
    thisView.totalPage= thisView.tableData.length

  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row,this.totalPage);
    },
    handleCurrentChange(val){
      this.tableData=[{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      },{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }]
      // console.log(thisView.totalPage);
    }
  },
}

</script>

<style lang="scss" scoped>
.card_header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.center {
  align-items: center;
}
</style>
