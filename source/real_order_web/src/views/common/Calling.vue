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
export default {
  methods: {
    next(){
      alert("下一位")
    },
    call(){
      alert("叫号")
    },
    menu(){
      this.$router.replace({name: 'Order'})
    },
    page(currentPage){
      const _this = this
      _this.tableData = [{
        o_id: '1',
        c_id: '王小虎'+currentPage,
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }, {
        o_id: '2',
        c_id: '王小虎',
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }, {
        o_id: '3',
        c_id: '王小虎',
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }, {
        o_id: '4',
        c_id: '王小虎',
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }, {
        o_id: '5',
        c_id: '王小虎',
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }, {
        o_id: '6',
        c_id: '王小虎',
        c_phone: '11111111111',
        o_start_time: '04:00:00',
        wait_time: '15'
      }]
    }
  },
  created() {
    const _this = this
    _this.total = 1000
    _this.tableData = [{
      o_id: '1',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }, {
      o_id: '2',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }, {
      o_id: '3',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }, {
      o_id: '4',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }, {
      o_id: '5',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }, {
      o_id: '6',
      c_id: '王小虎',
      c_phone: '11111111111',
      o_start_time: '04:00:00',
      wait_time: '15'
    }]
  },
  data() {
    return {
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
