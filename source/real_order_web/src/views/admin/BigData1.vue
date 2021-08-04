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
                label="Index clicked">
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
    setup() {

    },
    created() {
        this.$axios
            .get("http://localhost:8081/api/bigdata/index-count")
            .then(res => {
                console.log(res)
                this.tableData = res.data
            })
            .catch(err => {
                console.log("login err", err);
                ElMessage.error("读取失败" + err);
            });
    },
    data() {
        return {
            tableData: null
        }
    }
}
</script>
<style lang="scss" scoped>
</style>
