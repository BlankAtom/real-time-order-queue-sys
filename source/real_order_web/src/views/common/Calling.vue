<template>
  <div class="total">
    <el-container style="height: 100%">
      <el-header height="200px">
        <div>
          <div class="btn"><el-button type="primary" circle style="font-size: 40px" icon="el-icon-document">点单</el-button></div>
          <div class="btn"><el-button type="primary" circle style="font-size: 40px" icon="el-icon-phone">叫号</el-button></div>
          <div class="btn"><el-button type="primary" circle style="font-size: 60px" icon="el-icon-d-arrow-right"/></div>
        </div>
      </el-header>
      <el-main>
        <el-table
            :data="tableData"
            stripe
            style="width: 100%">
          <el-table-column
              prop="date"
              label="日期"
              width="180">
          </el-table-column>
          <el-table-column
              prop="name"
              label="姓名"
              width="180">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址">
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
    page(currentPage){
      const _this = this
      _this.total = 1000
      _this.tableData = [{
        date: '2016-05-02',
        name: '王小虎'+currentPage,
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
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }]
    }
  },
  created() {
    const _this = this
    _this.total = 1000
    _this.tableData = [{
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
    }, {
      date: '2016-05-01',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1519 弄'
    }, {
      date: '2016-05-01',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1519 弄'
    }]
  },
  data() {
    return {
      total: null,
      tableData: null
    }
  }
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
