<template>
  <div class="total">
    <el-container style="height: 100%">
      <el-main>
        <el-table
            :data="tableData"
            stripe
            style="width: 100%">
          <el-table-column
              prop="key"
              label="key">
          </el-table-column>
          <el-table-column
              prop="value"
              label="value">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {useStore} from "vuex";
import {ElMessage} from "element-plus";

export default {
  setup(){
    // this.$axios
    //     .get("/api/admin/bigdata?index=1")
    //     .then(res => {
    //       this.tableData = res.data
    //       // tabledata.wait_time =
    //     })
    //     .catch(err => {
    //       console.log("login err", err);
    //       ElMessage.error("读取失败" + err);
    //     });
  },
    created() {
        this.$axios
            .get("http://localhost:58081/api/spark/bigdata?index=2")
            .then(res => {
                console.log(res)
                var data = new Map()
                var red = res.data
                for (let i = 0; i < red; i++) {
                    data.set(red[i], red[red[i]])
                }
            })
            .catch(err => {
                console.log("login err", err);
                ElMessage.error("读取失败" + err);
            });
    },
  data(){
    return{
      tableData :null
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
