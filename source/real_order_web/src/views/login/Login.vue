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
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { encode } from "js-base64";
import {ElMessage} from "element-plus";
import axios from "../../utils/axios";

export default {
    setup() {
        const {proxy} = getCurrentInstance();
        const router = useRouter();
        const store = useStore();
        const loginFormRef = ref();

        const loginFormState = reactive({
            name: "",
            pwd: "",
            loading: false
        });

        const rules = {
            name: [{required: true, message: "账号不能为空", trigger: "blur"}],
            pwd: [
                {required: true, message: "密码不能为空", trigger: "blur"},
                {min: 5, max: 16, message: "密码长度为5-16位", trigger: "blur"}
            ]
        };

        const handleLogin = () => {
            loginFormRef.value.validate(valid => {
                if (!valid) {
                    return false;
                }

                loginFormState.loading = true;

                let params = {username: loginFormState.name, password: loginFormState.pwd};
                let roleGet = loginFormState.name;

                // proxy.$axios
                //     .post("/login", proxy.$qs.stringify(params))
                //     .then(res => {
                //         let {code, data, msg, success} = res.data;
                //         if (success === true) {
                //             store.dispatch("setToken", data)
                //         } else {
                //             ElMessage.error("登陆失败" + msg)
                //         }
                //
                //         console.log(res)
                //     })
                //     .catch(err => {
                //         console.log("login err", err);
                //         ElMessage.error("登录失败" + err);
                //     });


                setTimeout(() => {
                    // 这里主要是为了进行不验证登录，为了方便进行其他开发
                    let users = {
                        role: "user",
                        username: loginFormState.name
                    }
                    // if (loginFormState.name === "customer") {
                    //     users = {
                    //         role: loginFormState.name === "customer" ? "customer" : roleGet,
                    //         username: loginFormState.name
                    //     }
                    // } else if (loginFormState.name === "admin") {
                    //     users = {
                    //         role: loginFormState.name === "admin" ? "admin" : roleGet,
                    //         username: loginFormState.name
                    //     }
                    //
                    // } else {
                    //     users = {
                    //         role: loginFormState.name === "merchant" ? "merchant" : roleGet,
                    //         username: loginFormState.name
                    //     }
                    // }
                    Object.assign(params, users);
                    sessionStorage.setItem("jwt", encode(JSON.stringify(params)));
                    store.dispatch("setUser", params);
                    loginFormState.loading = false;
                    // 这里的替换需要改为指定页面
                    router.replace("/hxq");
                }, 1000);

            });
        };

        return {loginFormRef, loginFormState, rules, handleLogin};
    }
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
