<template>
  <div class="total">
    <el-container style="height: 100%">
      <el-main>
        <el-table
            :data="tableData"
            stripe
            style="width: 100%">
          <el-table-column
              prop="first"
              label="Merchant ID">
          </el-table-column>
          <el-table-column
              prop="second"
              label="Count">
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
            .get("http://localhost:8081/api/bigdata/merchant-count")
            .then(res => {
                console.log(res)
                this.tableData = res.data
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
