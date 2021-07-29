<template>
  <el-card shadow="never" class="index">
    <template #header>
      <div class="card_header">
        <b>用户列表</b>
      </div>
    </template>
    <div class="search" style="padding-left: 40%">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item >
        <el-input v-model="searchId" placeholder="请输入查询用户ID" clearable @clear="clear"  prefix-icon="el-icon-search"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="clearCurPage">查询</el-button>
      </el-form-item>
    </el-form>
    </div>
    <el-table
        :data="accounts"
        style="width: 100%">
      <el-table-column
          label="用户名"
          prop="username">
      </el-table-column>
      <el-table-column
          label="用户类型"
          prop="auth"
          :formatter="typeFormatter">
      </el-table-column>
      <el-table-column
          label="创建时间"
          prop="createTime">
      </el-table-column>
      <el-table-column
          label="更新时间"
          prop="updateTime">
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
              size="mini"
              type="primary"
              @click="showDialog(scope.$index,scope.row)">修改密码</el-button>
          <el-dialog
              title="修改密码"
              v-model="isShowDialog"
              width="30%"
              :before-close="handleClose">
              <el-form :model="updatePsw">
                <el-form-item label="输入密码">
                  <el-input v-model="updatePsw.firstPsw" placeholder="请输入大于6位的密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码">
                  <el-input v-model="updatePsw.secondPsw" placeholder="请输入大于6位的密码"></el-input>
                </el-form-item>
              </el-form>
            <el-button type="primary" @click="commitPsw">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
    <div  style="padding-top: 20px " align="center"  >
      <el-pagination
          layout="prev, pager, next"
          page-size="9"
          hide-on-single-page="true"
          background
          :total="total"
          @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </el-card>
</template>
<script>
import {ElMessage} from "element-plus";

export default {
    data() {
        return {
            updatePsw: {
                username: '',
                firstPsw: '',
                secondPsw: ''
            },
            isShowDialog: false,
            accounts: [],
            total: 9,
            searchId: '',
            curPage: 1
        }
    },
    created() {
        this.clearCurPage()
    },
    methods: {
        clearCurPage() {
            let params = {
                curPage: this.curPage,
                pageCount: 9,
                username: this.searchId
            }
            if (this.searchId === '') {
                this.$axios
                    .post('/admin/findAllAccount')
                    .then((response) => {
                        //获得所有页数显示的数据量
                        this.total = response.data.length
                    })
                    .catch((error) => {
                        console.log(error)
                    })
                //获取第一页的数据
                this.$axios
                    .post('/admin/findPageAccount', this.$qs.stringify(params))
                    .then((response) => {
                        this.accounts = response.data
                    }).catch((error) => {
                    console.log(error)
                })
            } else {
                //获取模糊查询所有用户数量
                this.$axios
                    .post('/admin/findAllByUsername', this.$qs.stringify(params))
                    .then(res => {
                        this.total = response.data.length
                    }).catch(err => {
                    console.log(error)
                })
                //获取模糊查询第一页用户
                this.$axios
                    .post('/admin/findPageByUsername', this.$qs.stringify(params))
                    .then((res) => {
                        this.accounts = response.data
                    }).catch((err) => {
                    console.log(error)
                })
                console.log(this.searchId)
            }
        },
        /**
         * 处理换页时的数据显示
         * @param val 页数
         */
        handleCurrentChange(val) {
            this.curPage = val
            this.clearCurPage()
        },
        /**
         * 通过输入的ID模糊查找所有用户
         */
        searchAccount() {
            let params = {username: this.searchId, curPage: this.curPage, pageCount: 9}
            console.log(this.searchId)
            //获取模糊查询所有用户数量
            this.$axios
                .post('/admin/findAllByUsername', this.$qs.stringify(params))
                .then((response) => {
                    this.total = response.data.length
                }).catch((error) => {
                console.log(error)
            })
            //获取模糊查询第一页用户
            this.$axios
                .post('/admin/findPageByUsername', this.$qs.stringify(params))
                .then((response) => {
                    this.accounts = response.data
                }).catch((error) => {
                console.log(error)
            })
            console.log(this.searchId)
        },
        /**
         * 根据输入密码进行修改
         */
        commitPsw() {
            let params = {username: this.updatePsw.username, psw: this.updatePsw.firstPsw}
            if (this.updatePsw.firstPsw.length <= 6 || this.updatePsw.secondPsw.length <= 6) {
                ElMessage.warning({
                    message: '密码长度要大于6',
                    type: 'warning'
                });
            } else if (this.updatePsw.firstPsw === this.updatePsw.secondPsw) {
                this.$axios
                    .post('/admin/updatePassword', this.$qs.stringify(params))
                    .then((response) => {
                        console.log(response)
                        if (response.data === 1) {
                            ElMessage.success({
                                message: '修改成功',
                                type: 'success'
                            });
                        }
                    }).catch((error) => {
                    console.log(error)
                })
            } else {
                ElMessage.error({
                    message: '输入两次密码不匹配',
                    type: 'error'
                });
            }
            this.isShowDialog = false
        },
        /**
         * 弹窗关闭
         */
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },
        /**
         * 弹窗显示
         */
        showDialog(index, row) {
            this.updatePsw = {
                username: row.username,
                firstPsw: '',
                secondPsw: ''
            }
            this.isShowDialog = true
        },
        /**
         * 弹窗显示取消按钮
         */
        cancel() {
            this.isShowDialog = false
        },
        /**
         * 用户类型格式转换
         * @param row 行
         * @param column 列
         * @param cellValue 值
         * @returns {string} 返回对应值
         */
        typeFormatter: function (row, column, cellValue) {
            if (cellValue === 1) {
                return "顾客账号"
            } else if (cellValue === 2) {
                return "商家账号"
            } else if (cellValue === 9) {
                return "管理员账号"
            }
        },
        /**
         * 搜索框的clearable对应事件
         * 重新获取后端数据
         */
        clear() {
            //获取所有数据
            // this.curPage = 1
            this.$axios
                .post('/admin/findAllAccount')
                .then((response) => {
                    //获得所有页数显示的数据量
                    this.total = response.data.length
                })
                .catch((error) => {
                    console.log(error)
                })
            let params = {curPage: this.curPage, pageCount: 9}
            //获取第一页的数据
            this.$axios
                .post('/admin/findPageAccount', this.$qs.stringify(params))
                .then((response) => {
                    this.accounts = response.data
                }).catch((error) => {
                console.log(error)
            })
        }
    }
}
</script>

<style lang="scss" scoped>
.card_header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>
