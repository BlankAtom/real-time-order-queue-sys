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
            <el-button style="float: right;font-size: 20px">结算</el-button>
            <el-tag style="float: left;font-size: 30px">总价：{{ total }}元</el-tag>
          </div>
        </el-footer>
      </el-container>
    </div>
</template>

<script>

export default {
  methods:{
    remove(row){
      if(row.count != 0){
        row.count--
        this.total -= row.price
      }
    },
    add(row){
      row.count++
      this.total += row.price
    },
  },
  data() {
    return {
      total: 0,
      tableData: [{
        name: '2016-05-02',
        price: 1,
        count: '0'
      }, {
        name: '2016-05-04',
        price: 2,
        count: '0'
      }, {
        name: '2016-05-01',
        price: 3,
        count: '0'
      }, {
        name: '2016-05-03',
        price: 4,
        count: '0'
      }, {
        name: '2016-05-04',
        price: 5,
        count: '0'
      }, {
        name: '2016-05-04',
        price: 6,
        count: '0'
      }]
    }
  }
};
</script>

<style>
</style>
