<template>
    <div class="login">
        <el-card class="login_center">
            <template #header>
                <div class="card_header">
                    <span>用户登录</span>
                </div>
            </template>
            <el-form :model="loginFormState" :rules="rules" ref="loginFormRef">
                <el-form-item prop="name">
                    <el-input
                        prefix-icon="el-icon-user-solid"
                        v-model.trim="loginFormState.name"
                        maxlength="32"
                        placeholder="请输入账号"
                        clearable
                    ></el-input>
                </el-form-item>
                <el-form-item prop="pwd">
                    <el-input
                        prefix-icon="el-icon-lock"
                        v-model.trim="loginFormState.pwd"
                        maxlength="16"
                        show-password
                        placeholder="请输入密码"
                        clearable
                        @keyup.enter.exact="handleLogin"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width: 100%" :loading="loginFormState.loading" @click="handleLogin">登 录</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import { getCurrentInstance, reactive, ref } from "vue";
import { useStore } from "vuex";
import {ElMessage} from "element-plus";
import { encode } from "js-base64"
import router from "../../router";

export default {
    setup() {
    },
    data() {
        return {
            rules: {
                name: [{required: true, message: "账号不能为空", trigger: "blur"}],
                pwd: [
                    {required: true, message: "密码不能为空", trigger: "blur"},
                    {min: 5, max: 16, message: "密码长度为5-16位", trigger: "blur"}
                ]
            },
            // router: useRouter(),
            // store: useStore(),
            // proxy: getCurrentInstance(),
            loginFormState: {
                name: "",
                pwd: "",
                loading: false
            }
        }
    },
    methods: {
        handleLogin() {
            window.sessionStorage.setItem('cid', this.loginFormState.name)

            this.loginFormState.loading = true;

            let params = {username: this.loginFormState.name, password: this.loginFormState.pwd};
            // 调用登录api
            this.$axios
                .post("/user/login", params)
                .then(res => {
                    let {data, msg, success, code} = res.data;
                    console.log(res.data)
                    if (success === true) {
                        this.$store.dispatch("setToken", data)

                        sessionStorage.setItem("state", JSON.stringify(Object.assign({}, this.$store.state)))
                        this.loginFormState.loading = false;

                        // console.log()
                        let next = "/" + data.auth

                        console.log(next)
                        ElMessage.success("登陆成功" + msg)
                        // let ur = useRouter()
                        // ur.replace(next)
                        setTimeout(() => {
                            router.replace({path: next})
                        }, 1000)
                    } else {
                        ElMessage.error("登陆失败(then)" + msg + " Code:" + code)
                    }
                })
                .catch(err => {
                    ElMessage.error("登录失败" + err);
                    this.loginFormState.loading = false;
                });

            router.replace("/merchant")
            // this.router.push({path: "/merchant", replace: true})
        },
    },
};
</script>

<style lang="scss" scoped>
.login {
    width: 100vw;
    height: 100vh;
    background-image: url("../../assets/img/login.jpg");
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    .login_center {
        width: 396px;
        height: auto;
    }

    .card_header {
        font-size: 18px;
        text-align: center;
    }
}
</style>
