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
        </div>

    </div>
</template>

<script>
import {getCurrentInstance} from "vue";

export default {
    data() {
        return {
            testData: [],
            isPC: document.documentElement.clientWidth > 640,
            tableData: [{
                pic: '2016-05-02',
                name: '王小虎',
                mId: '1518 弄'
            }, {
                pic: '2016-05-04',
                name: '乐色',
                mId: '1517 弄'
            }, {
                pic: '2016-05-01',
                name: '王小虎',
                mId: '1519 弄'
            }, {
                pic: '2016-05-03',
                name: '王小虎',
                mId: '1516 弄'
            }]
        }
    },
    created() {
        const _this = this
        const {proxy} = getCurrentInstance();
        proxy.$axios.post('cus/showmerchant')
        .then((response)=>{
            this.testData = response.data
            console.log(this.testData)
        }).catch((error)=>{
            console.log(error)
        })
    },
    methods: {
        clickInto(val) {
            let thisRowData = this
            thisRowData = val
            let m_id = val.m_id
            console.log("mid is "+m_id)
            this.lineUp(m_id)

        },
        lineUp(mId) {
            console.log(mId)
            this.$router.push("/queue" )
            this.$router.push("/queue/"+mId)
        }
    },

}
</script>

<style>


</style>