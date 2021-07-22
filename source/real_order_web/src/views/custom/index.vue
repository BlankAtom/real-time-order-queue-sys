<template>
    <div>
        <h1>Customer</h1>
        <div class="cm-div">

            <el-table @row-click="clickInto"
                      :data="tableData"
                      style="width: 100%">
                <el-table-column
                    min-width="30%"
                    label="图片"
                    align="center"
                >
                    <img src="../../assets/img/admin.png" style="height: 80px;">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="商家名称"
                    align="center"
                >
                </el-table-column>
                <el-table-column hidden
                                 prop="mId"
                                 align="center"
                >
                </el-table-column>
            </el-table>
        </div>

    </div>
</template>

<script>
import {getCurrentInstance} from "vue";

export default {
    // setup(){
    //     const {proxy} = getCurrentInstance();
    //     proxy.$axios.get('cus/showmerchant',{
    //         params:{
    //
    //         }
    //     })
    //         .then(function (response){
    //             console.log("6854")
    //             console.log(response.data)
    //             let {a,b,c,d}=response.data
    //             console.log(a)
    //         })
    //         .catch(function (error){
    //             console.log(error)
    //         })
    // },
    methods: {
        clickInto(val) {
            let thisRowData = this
            thisRowData = val
            let mId = val.mId
            this.lineUp(mId)
            console.log(val.pic)
        },
        lineUp(mId) {
            console.log(mId)
            this.$router.push("/queue" )
            this.$router.push("/queue/"+mId)
        }
    },
    data() {
        return {
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
}
</script>

<style>


</style>