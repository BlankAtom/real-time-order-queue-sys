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
                <el-col span="18">
                <el-input v-model="searchId" placeholder="请输入查询订单ID" clearable @clear="clear"  prefix-icon="el-icon-search"></el-input>
                </el-col>
              </el-form-item>
              <el-form-item>
                <el-select v-model="value" placeholder="请选择" default-first-option="true" >
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
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
<!--          <el-table-column-->
<!--              label="商家ID"-->
<!--              prop="m_id">-->
<!--          </el-table-column>-->
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
      value: 1,
      options: [{
        value: 1,
        label: '订单ID'
      }, {
        value: 2,
        label: '用户ID'
      }],
      m_id: 'merchant_8995566',
      searchId: '',
      orders: [],
      total: 7,
      curPage: 1
    }
  },
  created() {
    let params = {m_id: this.m_id,curPage: this.curPage,pageCount: 9}
    //获取所有的数据信息
    this.$axios
        .post('/data/findAll',this.$qs.stringify(params))
        .then((response)=>{
          this.total = response.data.length
        }).catch((error)=>{
      console.log(error)
    })
    //获取第一页的数据
    this.$axios
        .post('/data/findPage',this.$qs.stringify(params))
        .then((response)=>{
          this.orders = response.data
        }).catch((error)=>{
          console.log(error)
      })

  },
  methods: {
    /**
     * 通过输入的ID模糊查找所有用户或者订单
     */
    searchAccount(){
      console.log(this.value)
      let params = {searchId: this.searchId,m_id: this.m_id,curPage:this.curPage,pageCount:9 ,choose:this.value}
      //获取模糊查询所有订单数量
      this.$axios
          .post('/data/findAllById',this.$qs.stringify(params))
          .then((response)=>{
              this.total = response.data.length
          }).catch((error)=>{
            console.log(error)
      })
      //获取模糊查询一页的
      this.$axios
          .post('/data/findPageById',this.$qs.stringify(params))
          .then((response)=>{
            this.orders = response.data
          }).catch((error)=>{
        console.log(error)
      })
    },
    /**
     * 处理换页时的数据显示
     * @param val 页数
     */
    handleCurrentChange(val){
      this.curPage=val
      let params = {m_id: this.m_id,curPage: this.curPage,pageCount: 9,searchId:this.searchId,choose: this.value}
      //搜索框为空点击页码获取对应页码的订单
      if(this.searchId===''){
        this.$axios
            .post('/data/findPage',this.$qs.stringify(params))
            .then((response)=>{
              this.orders = response.data
              // console.log(this.orders)
            }).catch((error)=>{
          console.log(error)
        })
      }else{//有搜索时点击页码获取对应订单
        this.$axios
            .post('/data/findPageById',this.$qs.stringify(params))
            .then((response)=>{
              this.orders = response.data
            }).catch((error)=>{
          console.log(error)
        })
      }
    },
    /**
     * 删除按钮事件
     * @param index 行数
     * @param row 一行的数据
     */
    handleDelete(index, row) {
      let params = {o_id: row.o_id}
      this.$axios
          .post('/data/deleteOrderByo_id',this.$qs.stringify(params))
          .then((response)=>{
            ElMessage.success({
              message: '删除成功',
              type: 'success'
            });
          })
          .catch((err)=>{
            console.log(err)
          })
      this.orders.splice(index,1)
    },
    /**
     * 付款方式格式转换
     * @param row 行数
     * @param column 列数
     * @param cellValue 值
     * @returns {string} 返回对应值
     */
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
    /**
     * 状态格式转换
     * @param row 行数
     * @param column 列数
     * @param cellValue 值
     * @returns {string} 返回对应值
     */
    statusFormatter: function (row,column,cellValue){
      if(cellValue==="closed"){
        return "订单已关闭";
      }else if (cellValue==="opened"){
        return "订单还在继续"
      }
    },
    /**
     * 搜索框的clearable对应事件
     * 重新获取后端数据
     */
    clear(){
      this.curPage = 1
      //获取所有的数据信息
      let params = {m_id: this.m_id,curPage: this.curPage,pageCount: 9}
      this.$axios
          .post('/data/findAll',this.$qs.stringify(params))
          .then((response)=>{
            this.total = response.data.length
          }).catch((error)=>{
        console.log(error)
      })
      //获取第一页的数据
      this.$axios
          .post('/data/findPage',this.$qs.stringify(params))
          .then((response)=>{
            this.orders = response.data
          }).catch((error)=>{
        console.log(error)
      })
    }
  }
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
