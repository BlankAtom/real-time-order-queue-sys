<template>
  <el-card shadow="never" class="index">
    <template #header>
      <div class="card_header">
        <b>商家列表</b>
      </div>
    </template>
<!--    <div  style="padding-left: 40%">-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item align="left">
          <el-button type="primary" @click="showCreateDialog">新建商家</el-button>
        </el-form-item>
        <el-form-item  style="padding-left: 40%">
          <el-input v-model="searchName" placeholder="请输入查询用户ID" clearable @clear="clear"  prefix-icon="el-icon-search"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="clearCurPage">查询</el-button>
        </el-form-item>
      </el-form>
<!--    </div>-->
    <el-table
        :data="merchants"
        style="width: 100%">
<!--      <el-table-column-->
<!--          label="用户名"-->
<!--          prop=""-->
<!--      >-->
<!--      </el-table-column>-->
      <el-table-column
          label="用户名"
          prop="m_name">
      </el-table-column>
      <el-table-column
          label="手机号"
          prop="m_phone">
      </el-table-column>
      <el-table-column
          label="地址"
          prop="m_address">
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" type="primary" @click="showUpdateDialog(scope.$index,scope.row)">修改信息</el-button>
          <el-button size="mini" type="primary" @click="showUpdatePSW(scope.$index,scope.row)">修改密码</el-button>
          <el-button size="mini" type="danger" @click="deleteMerchant(scope.$index,scope.row)">删除账号</el-button>
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
<!--  //创建一个新用户-->
  <el-dialog
      title="创建用户"
      v-model="isShowCreateMerchantDialog"
      width="30%"
      :before-close="handleClose">
      <el-form :model="newMerchant" :label-position="labelPosition" label-width="80px" status-icon :rules="rules" ref="newMerchant">
        <el-form-item label="手机号" prop="phone">
          <el-input v-model.number="newMerchant.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="输入密码" prop="psw">
          <el-input type="password" v-model="newMerchant.psw" placeholder="请输入大于6位的密码" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="psw2">
          <el-input type="password" v-model="newMerchant.psw2" placeholder="请再次输入密码" autocomplete="on"></el-input>
        </el-form-item>
        <el-form-item label="输入名字" prop="name">
          <el-input v-model="newMerchant.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="许可证" prop="license">
          <el-input v-model.number="newMerchant.license" placeholder="请输入许可证"></el-input>
        </el-form-item>
        <el-form-item label="开业时间">
          <el-col :span="10">
            <el-time-picker v-model="newMerchant.openTime" placeholder="开门时间" style="width: 100%" format="HH:mm" value-format="HH:mm"></el-time-picker>
          </el-col>
          <el-col class="line" :span="2">---</el-col>
          <el-col :span="10">
            <el-time-picker v-model="newMerchant.closeTime" placeholder="休息时间" style="width: 100%" format="HH:mm" value-format="HH:mm"></el-time-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="newMerchant.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="主营内容">
          <el-input v-model="newMerchant.tag" placeholder="请输入主营内容"></el-input>
        </el-form-item>
        <el-form-item label="注意事项">
          <el-input v-model="newMerchant.notice" placeholder="请输入注意事项"></el-input>
        </el-form-item>
        <el-form-item label="商标">
          <el-input v-model="newMerchant.icon" placeholder="请输入商标"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('newMerchant')">提交</el-button>
          <el-button @click="resetForm('newMerchant')">重置</el-button>
        </el-form-item>
      </el-form>
  </el-dialog>
<!--  //修改信息dialog-->
  <el-dialog
      title="修改信息"
      v-model="isShowUpdateMerchantDialog"
      width="30%"
      :before-close="handleClose">
    <el-form :model="updateMerchant" :label-position="labelPosition" label-width="80px"  ref="updateMerchant">
      <el-form-item label="输入名字" >
        <el-input v-model="updateMerchant.name" placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="许可证" >
        <el-input v-model.number="updateMerchant.license" placeholder="请输入许可证" ></el-input>
      </el-form-item>
      <el-form-item label="开业时间">
        <el-col :span="10">
          <el-time-picker v-model="updateMerchant.openTime" placeholder="开门时间" style="width: 100%" format="HH:mm" value-format="HH:mm"></el-time-picker>
        </el-col>
        <el-col class="line" :span="2">---</el-col>
        <el-col :span="10">
          <el-time-picker v-model="updateMerchant.closeTime" placeholder="休息时间" style="width: 100%" format="HH:mm" value-format="HH:mm"></el-time-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="updateMerchant.address" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="主营内容">
        <el-input v-model="updateMerchant.tag" placeholder="请输入主营内容"></el-input>
      </el-form-item>
      <el-form-item label="注意事项">
        <el-input v-model="updateMerchant.notice" placeholder="请输入注意事项"></el-input>
      </el-form-item>
      <el-form-item label="商标">
        <el-input v-model="updateMerchant.icon" placeholder="请输入商标"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="update('updateMerchant')">提交</el-button>
        <el-button @click="resetForm('updateMerchant')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
<!--  //修改密码-->
  <el-dialog
      title="修改商家密码"
      v-model="isShowUpdatePSWDialog"
      width="30%"
      :before-close="handleClose">
    <el-form :model="newMerchant" :label-position="labelPosition" label-width="80px" status-icon :rules="rules" ref="newMerchant">
      <el-form-item label="输入密码" prop="psw">
        <el-input type="password" v-model="newMerchant.psw" placeholder="请输入大于6位的密码" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="psw2">
        <el-input type="password" v-model="newMerchant.psw2" placeholder="请再次输入密码" autocomplete="on"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updatePSW('newMerchant')">提交</el-button>
        <el-button @click="resetForm('newMerchant')">重置</el-button>
      </el-form-item>
    </el-form>

  </el-dialog>
</template>
<script>
export default {
  data(){
    const phoneLength = /^1[3456789]\d{9}$/;
    /**
     *检查手机号是否正确
     */
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'))
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          // const reg = /^1[3456789]\d{9}$/;
          if (!phoneLength.test(value)) {
            callback(new Error('请输入11位的手机号'));
          } else {
            callback()
          }
        }
      }, 1000)
    };
    /**
     * 第一次输入密码
     * @param rule
     * @param value
     * @param callback
     * @constructor
     */
    const firstPsw = (rule,value,callback)=>{
      if (value === '') {
        callback(new Error('请输入密码'));
      }else if(value.length<=6){
        callback(new Error('请输入至少7位密码'));
      }else {
        if (this.newMerchant.psw2 !== '') {
          this.$refs.newMerchant.validateField('psw2');
        }
        callback();
      }
    };
    /**
     * 第二次输入密码
     * @param rule
     * @param value
     * @param callback
     * @constructor
     */
    const secondPsw = (rule,value,callback)=>{
      // console.log(this)
      if (value === '') {
        callback(new Error('请再次输入密码'));
      }else if(value.length<7){
        callback(new Error('请输入至少7位密码'));
      }else if (value !== this.newMerchant.psw) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
    /**
     * 检查许可证是否为纯数字
     * @param rule
     * @param value
     * @param callback
     * @returns {*}
     * @constructor
     */
    const checkLicense = (rule,value,callback)=>{
      console.log(this.newMerchant.license)
      if (!value) {
        return callback(new Error('许可证不能为空'))
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          callback()
        }
      }, 1000)
    }
    /**
     * 检查输入名字是否为空
     * @param rule
     * @param value
     * @param callback
     * @returns {*}
     */
    const checkName =(rule,value,callback)=>{
      console.log(this.newMerchant.openTime+" "+this.newMerchant.closeTime)
      if (!value) {
        return callback(new Error('名字不能为空'))
      }else
        callback()
    }
    return{
      labelPosition: 'right',
      searchName:'',
      curPage:1,
      total: 9,
      merchants:[],
      isShowCreateMerchantDialog: false,
      isShowUpdateMerchantDialog: false,
      isShowUpdatePSWDialog:false,
      newMerchant:{
        m_id: '',
        phone:'',
        psw: '',
        psw2: '',
        name: '',
        license:'',

        address: '',
        openTime: '',
        closeTime: '',
        tag: '',
        notice: '',
        icon: ''
      },
      updateMerchant:{
        m_id: '',
        phone:'',
        psw: '',
        psw2: '',
        name: '',
        license:'',

        address: '',
        openTime: '',
        closeTime: '',
        tag: '',
        notice: '',
        icon: ''
      },
      updateAccount:{
        m_id:'',
        psw:'',
        psw2:''
      },
      rules: {
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ],
        psw: [
          {validator: firstPsw,trigger: 'blur'}
        ],
        psw2: [
          {validator: secondPsw,trigger: 'blur'}
        ],
        license: [
          { validator: checkLicense, trigger: 'blur' }
        ],
        name: [
            {validator:checkName,trigger: 'blur'}
        ]
      }
    }
  },
  created(){
    this.clearCurPage()
  },
  methods: {
    /**
     * 重新加载当前页面
     */
    clearCurPage(){
      let params = {m_name:this.searchName,curPage:this.curPage,pageCount:9}
      if(this.searchName==='') {
        this.$axios
            .post('/merchant/findAllMerchant')
            .then((response) => {
              console.log(response.data.length)
              this.total = response.data.length
            }).catch((error) => {
          console.log(error)
        })
        let params = {curPage: this.curPage, pageCount: 9}
        this.$axios
            .post('/merchant/findPageMerchant', this.$qs.stringify(params))
            .then((response) => {
              this.merchants = response.data
            }).catch((error) => {
          console.log(error)
        })
      }else{
        this.$axios
            .post('/merchant/findAllBym_name',this.$qs.stringify(params))
            .then((response)=>{
              this.total = response.data.length
              // this.merchants = response.data
            }).catch((error)=>{
          console.log(error)
        })
        this.$axios
            .post('/merchant/findPageBym_name',this.$qs.stringify(params))
            .then((response)=>{
              // this.total = response.data.length
              this.merchants = response.data
            }).catch((error)=>{
          console.log(error)
        })
      }
    },
    /**
     * 处理换页时的数据显示
     * @param val 页数
     */
    handleCurrentChange(val){
      this.curPage = val
      this.clearCurPage()
    },
    /**
     * 插入一个新Merchant
     */
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post('/merchant/insert',this.$qs.stringify(this.newMerchant))
          .then((response)=>{
            console.log(response.data)
          })
          .catch((error)=>{
            console.log(error)
          })
          this.isShowCreateMerchantDialog = false
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.clearCurPage()
    },
    /**
     * 修改商家信息
     */
    update(formName){
      this.$axios
          .post('/merchant/updateMerchant',this.$qs.stringify(this.updateMerchant))
          .then((response)=>{
              if(response.data===1){
                ElMessage.success({
                  message: '修改成功',
                  type: 'success'
                });
              }
          }).catch((error)=>{
            console.log(error)
      })
      this.$refs[formName].resetFields()
      this.isShowUpdateMerchantDialog = false
      this.clearCurPage()
    },
    /**
     * 修改商家密码
     */
    updatePSW(formName){
      this.updateAccount.psw = this.newMerchant.psw
      this.$refs[formName].resetFields()
      let params = {username:this.updateAccount.m_id,psw:this.updateAccount.psw}
      console.log(params)
      this.$axios
          .post('/admin/updatePassword',this.$qs.stringify(params))
          .then((response)=>{
            ElMessage.success({
              message: '修改成功',
              type: 'success'
            });
          }).catch((error)=>{
            console.log(error)
      })
      this.isShowUpdatePSWDialog = false
      this.clearCurPage()
    },
    /**
     * 删除该行的数据
     */
    deleteMerchant(index,row){
      let params = {m_id:row.m_id}
      this.$axios
          .post('/merchant/deleteMerchantBym_id',this.$qs.stringify(params))
          .then((response)=>{
            ElMessage.success({
              message: '删除成功',
              type: 'success'
            });
          }).catch((error)=>{
            console.log(error)
      })
      this.clearCurPage()
    },
    /**
     * 重置表单
     */
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    /**
     *展示新建商家表单
     */
    showCreateDialog(){
      this.isShowCreateMerchantDialog = true
    },
    /**
     * 展示修改商家信息dialog
     */
    showUpdateDialog(index,row){
      this.updateMerchant.m_id = row.m_id
      this.updateMerchant.name = row.m_name
      this.updateMerchant.phone = row.m_phone
      // console.log(this.updateMerchant.phone)
      this.updateMerchant.openTime = row.m_open_time
      this.updateMerchant.closeTime = row.m_close_time
      this.updateMerchant.address = row.m_address
      this.updateMerchant.tag = row.m_tag
      this.updateMerchant.notice = row.m_notice
      this.updateMerchant.icon = row.m_icon
      this.updateMerchant.license = row.m_license
      this.isShowUpdateMerchantDialog = true
    },
    /**
     * 展示修改商家密码
     */
    showUpdatePSW(index,row){
      this.updateAccount.m_id=row.m_id
      this.isShowUpdatePSWDialog = true
    },
    /**
     * 弹窗关闭
     */
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    /**
     * 删除searchName
     */
    clear(){
      this.clearCurPage()
    }
  }
}
</script>

<style scoped>

</style>