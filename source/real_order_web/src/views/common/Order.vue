<template>
    <div>
      <el-container>
        <el-header>
          <h1 style="text-align: center;font-size: 50px">点单</h1>
        </el-header>
        <el-main>
          <el-table
              :data="tableData"
              stripe
              style="width: 100%">
            <el-table-column
                prop="name"
                label="菜名">
            </el-table-column>
            <el-table-column
                prop="price"
                label="单价(元)">
            </el-table-column>
            <el-table-column
                prop="count"
                label="数量">
            </el-table-column>
            <el-table-column>
              <template #default="sc">
                <el-button icon="el-icon-minus" @click="remove(sc.row)"></el-button>
                <el-button icon="el-icon-plus" @click="add(sc.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
        <el-affix position="bottom" :offset="20">
        <el-footer height="10%" >
          <div>
            <el-button type="primary" style="float: right;font-size: 20px" @click="settlement()">结算</el-button>
            <el-select v-model="value" placeholder="请选择" style="float: right">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
            <el-tag style="float: left;font-size: 30px">总价：{{ totalPrice }}元</el-tag>
          </div>
        </el-footer>
        </el-affix>
      </el-container>
    </div>
</template>

<script>

import {ElMessage} from "element-plus";

export default {
  methods:{
    settlement(){
      this.$alert(this.content, '结算', {
        confirmButtonText: '结算',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    },
    show(){
      let detail
      for(let a in this.content){
        detail +=a
      }
    },
    remove(row){
      if(row.count != 0){
        row.count--
        let r1,r2,m,n;
        try{r1=this.totalPrice.toString().split(".")[1].length}catch(e){r1=0}
        try{r2=row.price.toString().split(".")[1].length}catch(e){r2=0}
        m=Math.pow(10,Math.max(r1,r2));
        n=(r1>=r2)?r1:r2
        this.totalPrice = ((this.totalPrice*m-row.price*m)/m).toFixed(n)
      }
    },
    add(row){
      row.count++
      let r1,r2,m;
      try{r1=this.totalPrice.toString().split(".")[1].length}catch(e){r1=0}
      try{r2=row.price.toString().split(".")[1].length}catch(e){r2=0}
      m=Math.pow(10,Math.max(r1,r2))
      this.totalPrice =  (this.totalPrice*m + row.price*m)/m
      let name = row.name
      if(row.count==1){
        this.content.name = row.count
      }
      else {
        this.content[row.name] = row.count;
      }
    },
    // page(currentPage) {
    //   const _this = this
    //   this.$axios
    //       .get("/calling/findOrder/"+currentPage+"/6/merchant_8995566")
    //       .then(res => {
    //         _this.tableData = res.data
    //       })
    //       .catch(err => {
    //         console.log("login err", err);
    //         ElMessage.error("读取失败" + err);
    //       });
    // }
  },
  created() {
    const _this = this
    this.c_id = this.$route.params.c_id
    this.$axios
        .get("/calling/findOrder/merchant_8995566")
        .then(res => {
          _this.tableData = res.data
          // _this.total = res.data[0].total
        })
        .catch(err => {
          console.log("login err", err);
          ElMessage.error("读取失败" + err);
        });
  },
  data() {
    return {
      totalPrice: 0.00,
      total: 0,
      value: null,
      content: null,
      tableData: null,
      options: [{
        value: '选项1',
        label: '现金支付'
      }, {
        value: '选项2',
        label: '微信支付'
      }, {
        value: '选项3',
        label: '支付宝支付'
      }, {
        value: '选项4',
        label: '刷银行卡支付'
      }, {
        value: '选项5',
        label: '刷信用卡支付'
      },{
        value: '选项6',
        label: '其他支付'
      }],
    }
  }
};
</script>

<style>
</style>
