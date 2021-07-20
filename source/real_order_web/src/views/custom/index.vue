<template>
    <div>
        <h1>Customer</h1>
<!--        <el-table-->
<!--            :data="tableData"-->
<!--            border-->
<!--            style="width: 100%">-->
<!--            <el-table-column-->
<!--                fixed-->
<!--                prop="date"-->
<!--                label="日期"-->
<!--                width="150">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                fixed="right"-->
<!--                label="操作"-->
<!--                width="100">-->
<!--                <template #default="scope">-->
<!--                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
<!--                    <el-button type="text" size="small">编辑</el-button>-->
<!--                </template>-->
<!--            </el-table-column>-->
<!--        </el-table>-->

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
            <el-table-column>
                <template #default="scope">
                    <el-button icon="el-icon-plus" @click="handleClick(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
export default {
    setup() {

    },
    methods: {
        handleClick(row) {
            console.log(row)
        }
    },
    data() {
        return {
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1518 弄',
                zip: 200333
            }, {
                date: '2016-05-04',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1517 弄',
                zip: 200333
            }, {
                date: '2016-05-01',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1519 弄',
                zip: 200333
            }, {
                date: '2016-05-03',
                name: '王小虎',
                province: '上海',
                city: '普陀区',
                address: '上海市普陀区金沙江路 1516 弄',
                zip: 200333
            }]
        }
    }
}
</script>

<style>


</style>