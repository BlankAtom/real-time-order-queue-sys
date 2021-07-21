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
                prop="date"
                label="菜名">
            </el-table-column>
            <el-table-column
                prop="name"
                label="单价">
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
          <el-pagination
              background
              layout="prev, pager, next"
              page-size="6"
              :total="1000"
              @current-change=""
              style="margin-left: 30%;"
              hide-on-single-page = true
          >
          </el-pagination>
        </el-main>
        <el-footer height="10%">
          <div>
            {{ total }}
          </div>
        </el-footer>
      </el-container>
    </div>
</template>

<script>

export default {
  setup() {

  },
  methods:{
    remove(row){
      if(row.count != 0){
        row.count--
        this.total -= row.name
      }
    },
    add(row){
      row.count++
      this.total += row.name
    },
  },
  data() {
    return {
      total: 0,
      tableData: [{
        date: '2016-05-02',
        name: 1,
        address: '上海市普陀区金沙江路 1518 弄',
        count: '0'
      }, {
        date: '2016-05-04',
        name: 2,
        address: '上海市普陀区金沙江路 1517 弄',
        count: '0'
      }, {
        date: '2016-05-01',
        name: 3,
        address: '上海市普陀区金沙江路 1519 弄',
        count: '0'
      }, {
        date: '2016-05-03',
        name: 4,
        address: '上海市普陀区金沙江路 1516 弄',
        count: '0'
      }, {
        date: '2016-05-04',
        name: 5,
        address: '上海市普陀区金沙江路 1517 弄',
        count: '0'
      }, {
        date: '2016-05-04',
        name: 6,
        address: '上海市普陀区金沙江路 1517 弄',
        count: '0'
      }]
    }
  }
};
</script>

<style>
</style>
