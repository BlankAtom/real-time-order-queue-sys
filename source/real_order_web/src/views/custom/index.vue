<template>
    <div>
        <div class="search" style="margin:auto;width: max-content">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item>
                    <el-col span="18">
                        <el-input v-model="searchKey" placeholder="请输入查询关键字" clearable @clear="clear"
                                  prefix-icon="el-icon-search"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="searchMerchant">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
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
            <div id="123" style="padding-top: 20px " align="center">
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
                cid:window.sessionStorage.getItem('cid'),
                total: 6,
                testData: [],
                searchKey: "",
                isPC: document.documentElement.clientWidth > 640
            }
        },
        created() {
            const _this = this
            const {proxy} = getCurrentInstance();
            this.$axios.post('cus/showmerchant')
                .then((response) => {
                    this.total = response.data.length
                    console.log(this.testData)
                }).catch((error) => {
                console.log(error)
            })
            this.$axios
                .post('/cus/merchantByPage?curPage=1&pageCount=8&key=')
                .then((response) => {
                    this.testData = response.data
                }).catch((error) => {
                console.log(error)
            })
        },
        methods: {
            searchMerchant() {
                if (this.searchKey!=="") {
                    console.log("the key is " + this.searchKey)
                    //获取模糊查询所有订单数量
                    this.$axios
                        .post('/cus/findMerchantByKey?key=' + this.searchKey)
                        .then((response) => {
                            this.total = response.data.length
                        }).catch((error) => {
                        console.log(error)
                    })
                    this.$axios
                        .post('/cus/merchantByPage?curPage=1&pageCount=8&key=' + this.searchKey)
                        .then((response) => {
                            this.testData = response.data
                        }).catch((error) => {
                        console.log(error)
                    })
                }
            },
            clear() {
                //获取所有的数据信息
                this.$axios
                    .post('cus/showmerchant')
                    .then((response) => {
                        this.total = response.data.length
                    }).catch((error) => {
                    console.log(error)
                })
                this.$axios
                    .post('/cus/merchantByPage?curPage=1&pageCount=8?key=')
                    .then((response) => {
                        this.testData = response.data
                    }).catch((error) => {
                    console.log(error)
                })
            },
            handleCurrentChange(val) {
                this.$axios
                    .post('/cus/merchantByPage?curPage=' + val + '&pageCount=8&key=' + this.searchKey)
                    .then((response) => {
                        console.log("val " + val)
                        this.testData = response.data
                    }).catch((error) => {
                    console.log(error)
                })
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
                this.$axios
                    .post('/cus/isQueuing?c_id=' + window.sessionStorage.getItem('cid'))
                    .then((response) => {
                        if (response.data===0)
                            this.$router.push("/hxq/queue/" + mId)
                        else
                        {
                            alert("您已经在别家店铺的队列中了哟")
                            this.$router.push("/hxq/myqueue")
                        }
                    }).catch((error) => {
                    console.log(error)
                })

            }
        },

    }
</script>

<style>


</style>