<template>
  <div class="total">
    <el-container style="height: 100%">
      <el-header height="200px">
        <div>
          <div class="btn"><el-button type="primary" circle style="font-size: 40px" icon="el-icon-document" @click.native="menu()">点单</el-button></div>
          <div class="btn"><el-button type="primary" circle style="font-size: 40px" icon="el-icon-phone" @click.native="call()">叫号</el-button></div>
          <div class="btn"><el-button type="primary" circle style="font-size: 60px" icon="el-icon-d-arrow-right" @click.native="next()"/></div>
        </div>
      </el-header>
      <el-main>
        <el-table
            :data="tableData"
            stripe
            style="width: 100%">
          <el-table-column
              prop="o_id"
              label="排队号">
          </el-table-column>
          <el-table-column
              prop="c_id"
              label="用户ID">
          </el-table-column>
          <el-table-column
              prop="c_phone"
              label="电话">
          </el-table-column>
          <el-table-column
              prop="o_start_time"
              label="开始排队时间">
          </el-table-column>
          <el-table-column
              prop="wait_time"
              label="等待时间(分钟)">
          </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            page-size="6"
            :total="total"
            @current-change="page"
            style="margin-left: 30%;"
            hide-on-single-page = true
        >
        </el-pagination>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import {getCurrentInstance} from "vue";

export default {
  setup() {
    const {proxy} = getCurrentInstance();
  },
  methods: {
    next(){
      this.$axios
          .get("/calling/closeOrder/"+this.tableData[0].o_id)
          .then(res => {
            this.$router.go(0)
          })
          .catch(err => {
            console.log("login err", err);
            ElMessage.error("读取失败" + err);
          });
    },
    call(){
      alert("叫号")
    },
    menu(){
      // this.$router.replace({name: 'Order'})+this.tableData[0].c_id
      this.$router.push({
        // path: 'yourPath',
        name: 'Order',
        params: {
          c_id: this.tableData[0].c_id,
          o_id: this.tableData[0].o_id
        }
      })
    },
    page(currentPage) {
      // const {proxy} = getCurrentInstance();
      const _this = this
      this.$axios
          .get("/calling/findAll/"+currentPage+"/6/merchant_8995566")
          .then(res => {
            _this.tableData = res.data
            // tabledata.wait_time =
          })
          .catch(err => {
            console.log("login err", err);
            ElMessage.error("读取失败" + err);
          });
    }
  },
  created() {
    const _this = this

    this.$axios
        .get("/calling/findAll/1/6/merchant_8995566")
        .then(res => {
          _this.tableData = res.data
          _this.total = res.data[0].total
        })
        .catch(err => {
          console.log("login err", err);
          ElMessage.error("读取失败" + err);
        });
  },
  data() {
    return {
      p: 1,
      c: 6,
      total: null,
      tableData: null
    }
  },
};
</script>
<style lang="scss" scoped>
.total {
  width: 100%;
  height: 100%;
}
.el-button{
  width: 200px;
  height: 200px;
}
.btn{
  float: left;
  width: 33.33%;
  text-align: center;
}
</style>
