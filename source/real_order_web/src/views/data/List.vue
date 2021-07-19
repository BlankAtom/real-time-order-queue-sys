<!--<script src="//unpkg.com/vue/dist/vue.js"></script>-->
<!--<script src="//unpkg.com/element-ui@2.15.3/lib/index.js"></script>-->
<template>
    <el-card shadow="never" class="index">
        <template #header>
            <div class="card_header">
                <b>数据列表</b>
            </div>
        </template>
<!--        <el-empty description="暂无数据1111"></el-empty>-->
<!--      <div id="app">-->
<!--      <template>-->
<!--      </template>-->
<!--      </div>-->
        <el-table
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
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
          <el-table-column
              align="right">
<!--            <template slot="header" slot-scope="scope">-->
<!--              <el-input-->
<!--                  v-model="search"-->
<!--                  size="mini"-->
<!--                  placeholder="输入关键字搜索"/>-->
<!--            </template>-->
<!--            <template slot-scope="scope">-->
              <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
<!--            </template>-->
          </el-table-column>
        </el-table>

    </el-card>
</template>

<script >
export default {
  data() {
    return {
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
      }]
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
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
</style>
