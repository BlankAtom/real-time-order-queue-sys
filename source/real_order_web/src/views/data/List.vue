<template>
    <el-card shadow="never" class="index">
        <template #header>
            <div class="card_header">
                <b>数据列表</b>
            </div>
        </template>
        <div class="search" style="padding-left: 40%">
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item >
              <el-input v-model="searchId" placeholder="请输入查询订单ID" clearable @clear="clear"  prefix-icon="el-icon-search"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="searchAccount">查询</el-button>
            </el-form-item>
          </el-form>
        </div>
        <el-table
            :data="orders"
            style="width: 100%">
          <el-table-column
              label="订单ID"
              prop="o_id">
          </el-table-column>
          <el-table-column
              label="商家ID"
              prop="m_id">
          </el-table-column>
          <el-table-column
              label="用户ID"
              prop="c_id">
          </el-table-column>
          <el-table-column
              label="花费金额"
              prop="o_cost">
          </el-table-column>

          <el-table-column
              label="下单时间"
              prop="o_start_time">
          </el-table-column>
          <el-table-column
              label="付款时间"
              prop="o_pay_time">
          </el-table-column>
          <el-table-column
              label="付款方式"
              prop="o_pay_type"
              :formatter="typeFormatter">
          </el-table-column>
          <el-table-column
              label="状态"
              prop="status"
              :formatter="statusFormatter">
          </el-table-column>
          <el-table-column
              label="桌号"
              prop="o_desk_num">
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">

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
          page-size="9"
          hide-on-single-page="true"
          background
          :total="total"
          @current-change="handleCurrentChange">
      </el-pagination>
      </div>

    </el-card>
</template>

<script >
import {ElMessage} from "element-plus";
export default {
  data(){
    return {
      searchId: '',
      orders: [],
      currentPage: 1,
      total: 7,
      currentData: [],
    }
  },
  created() {
    const params = {m_id: "merchant_8995566",curPage: 1,pageCount: 9}
    //获取所有的数据信息
    this.$axios
        .post('/data/findOrdersBym_id',this.$qs.stringify(params))
        .then((response)=>{
          this.total = response.data.length
        }).catch((error)=>{
      console.log(error)
    })
    //获取第一页的数据
    this.$axios
        .post('/data/findOrderByPage',this.$qs.stringify(params))
        .then((response)=>{
          this.orders = response.data
          // console.log(this.orders)
        }).catch((error)=>{
          console.log(error)
      })

  },
  methods: {
    //删除
    handleDelete(index, row) {
      console.log(row.o_id)
      let params = {o_id: row.o_id}
      this.$axios
          .post('/data/deleteOrderByo_id',this.$qs.stringify(params))
          .then((response)=>{
            ElMessage.success({
              message: '删除成功',
              type: 'success'
            });
            // this.reload
          })
          .catch((err)=>{
            console.log(err)
          })
      this.orders.splice(index,1)
      // console.log(index, row);
    },
    //点击页码获取当页的数据
    handleCurrentChange(val){
      this.currentPage=val
      // const thisView = this
      let params = {m_id: "merchant_8995566",curPage: val,pageCount: 9}
      this.$axios
          .post('/data/findOrderByPage',this.$qs.stringify(params))
          .then((response)=>{
            this.orders = response.data
            // console.log(this.orders)
          }).catch((error)=>{
        console.log(error)
      })
      // console.log(val);
    },
    typeFormatter: function (row,column,cellValue){
      if (cellValue===0){
        return "现金支付";
      }else if (cellValue===1){
        return "微信支付";
      }else if (cellValue===2){
        return "支付宝支付";
      }else if (cellValue===3){
        return "刷银行卡支付";
      }else if (cellValue===4){
        return "刷信用卡支付";
      }else if (cellValue===5){
        return "其他支付";
      }
    },
    statusFormatter: function (row,column,cellValue){
      if(cellValue==="closed"){
        return "订单已关闭";
      }else if (cellValue==="opened"){
        return "订单还在继续"
      }
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
