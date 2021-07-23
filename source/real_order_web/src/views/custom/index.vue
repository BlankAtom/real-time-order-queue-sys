<template>
    <div>
        <h1>Customer</h1>
        <div class="cm-div">
                <el-table @row-click="clickInto"
                          :data="testData"
                          style="width: 100%">
                    <el-table-column
                            min-width="30%"
                            label="图片"
                            align="center"
                    >
                        <img src="../../assets/img/admin.png" style="height: 80px;">
                    </el-table-column>
                    <el-table-column
                            prop="m_name"
                            label="商家名称"
                            align="center"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="m_address"
                            label="商家地址"
                            align="center"
                    >
                    </el-table-column>
                    <!--                <el-table-column v-if="false"-->
                    <!--                                 prop="m_id"-->
                    <!--                                 align="center"-->
                    <!--                >-->
                    <!--                </el-table-column>-->
                </el-table>
                <div id="123" style="padding-top: 20px " align="center"  >
                    <el-pagination
                            layout="prev, pager, next"
                            page-size="8"
                            hide-on-single-page="true"
                            background
                            :total="total"
                            @current-change="handleCurrentChange">
                    </el-pagination>
                </div>
        </div>

    </div>
</template>

<script>
    import {getCurrentInstance} from "vue";

    export default {
        data() {
            return {
                total:6,
                testData: [],
                isPC: document.documentElement.clientWidth > 640
            }
        },
        created() {
            const _this = this
            const {proxy} = getCurrentInstance();
            this.$axios.post('cus/showmerchant')
                .then((response) => {
                    // this.testData = response.data
                    this.total = response.data.length
                    console.log(this.testData)
                }).catch((error) => {
                console.log(error)
            })
            this.$axios
                .post('/cus/merchantByPage?curPage=1&pageCount=8')
                .then((response)=>{
                    this.testData = response.data
                }).catch((error)=>{
                console.log(error)
            })
        },
        methods: {
            handleCurrentChange(val){
                this.currentPage=val
                // const thisView = this
                let params = {curPage: val,pageCount: 6}
                this.$axios
                    .post('/cus/merchantByPage?curPage='+val+'&pageCount=8')
                    .then((response)=>{
                        console.log("val "+val)
                        this.testData = response.data
                    }).catch((error)=>{
                    console.log(error)
                })
                // console.log(val);
            },
            clickInto(val) {
                let thisRowData = this
                thisRowData = val
                let m_id = val.m_id
                console.log("mid is " + m_id)
                this.lineUp(m_id)

            },
            lineUp(mId) {
                console.log(mId)
                this.$router.push("/queue")
                this.$router.push("/queue/" + mId)
            }
        },

    }
</script>

<style>


</style>