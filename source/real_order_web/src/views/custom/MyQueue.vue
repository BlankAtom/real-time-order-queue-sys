<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center" style="margin-bottom: 20px">
            <h1 v-text="mName"></h1>

        </div>

<!--        圆形按钮+中心文字内容-->
        <div class="round-box" >
            <div class="round-anime-playing">
                <div class="round-queue-num" v-text="text"/>
            </div>

        </div>

        <div style="margin-top: 150px">
            <label style="color: black;size: auto;font-size: 40px" >前方共有<span style="color: darkgray;" v-text="qNub"></span>人排队</label>
        </div>

        <div style="margin-top: 80px">
            <el-button type="primary" class="btn-cancel-queue"
                       @click="cancel"
                       v-text="btnText">
            </el-button>
        </div>

    </div>


</template>
<script>
    import axios from "axios";
    import {getCurrentInstance} from "vue";

    export default {
        created() {
            this.$axios.post('merchant/findNumber?c_id='+window.sessionStorage.getItem('cid'))
                .then((response) => {
                    this.text = response.data+"号"
                }).catch((error) => {
                console.log(error)
            })
            this.$axios.post('merchant/findMyPosition?c_id='+window.sessionStorage.getItem('cid'))
                .then((response) => {
                    this.qNub = response.data
                }).catch((error) => {
                console.log(error)
            })
            const _this = this
            const {proxy} = getCurrentInstance();
            let m_id = this.m_id
            proxy.$axios.post('merchant/findMyQueue?c_id=' + window.sessionStorage.getItem('cid'))
                .then((response) => {
                    this.mName=response.data
                    console.log(response.data)
                    if (this.mName==="") {
                        this.$alert('您还没有选择商家哦，请进入首页选择商家并排队', '提示', {
                            confirmButtonText: '确定',
                            type: 'warning'
                        }).then(() => {
                            this.$router.push("/customer")
                        });
                        // alert("您还没有选择商家哦，请进入首页选择商家并排队")
                    }
                }).catch((error) => {
                console.log(error)
            })
        },
        data() {
            return {
                data: [],
                mName: '俺是个店名',
                text: 'XXXX号',
                qNub: 'XX',
                btnText: '取消排队',
                m_id: this.$route.params.mId,
            }
        },
        methods: {
            cancel(val) {
                this.$confirm('您选择了取消排队, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    center: true,
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '取消成功!'
                    },
                        this.$axios.post('merchant/cancel?c_id='+window.sessionStorage.getItem('cid'))
                            .then((response) => {
                            }).catch((error) => {
                            console.log(error)
                        }),
                        this.$router.push("/customer")
                    );

                }).catch(() => {
                    })
            }
        }
    }

</script>

<style scoped>
h1 {
    size: auto;
    margin-top: 50px;
    font-size: 50px;
    color: black;
}
.btn-cancel-queue {
    width: 200px;
    height: 60px;
    font-size: 30px;
    background-color: lightskyblue;
    border: none;
}
.btn-cancel-queue:hover {
    background-color: #46A3FF;
}
.round-queue-num {
    /*height: 300px;*/
    /*width: 300px;*/
    color: whitesmoke;
    text-align: center;
}
    .outer {
        /*background-image: url("../../assets/img/bluebackground.jpeg");*/
        background-color: whitesmoke;
        background-size: 100% 100%;

        height: 100%;
        position: fixed;
        width: 100%;
        text-align: center;
    }

    .center {
        width: max-content;
        margin: auto;
    }

.round-box {
    /*display: flex;*/
    /*align-items: center;*/

    /*position: relative;*/
    margin: 20px auto;
}
    .round-anime-normal, .round-anime-playing {
        /*position: absolute;*/
        margin: 0 auto;
        font-size: 50px;
        line-height: 300px;
        vertical-align: middle;
        user-select: none;
        z-index: -1;
        width: 300px;
        height: 300px;
        background: #46A3FF;
        border-radius: 100%;
    }
.round-anime-playing {
    -webkit-animation: round-anime-playing 3s infinite;
}
.round-anime-normal:active {
    width: 290px;
    height: 290px;
    margin-top: 10px;
    margin-bottom: 0px;
    line-height: 290px;
    font-size: 48px;
}



    @-webkit-keyframes round-anime-playing {
        0% {
            background: #46A3FF;
        }
        100% {
            background: #46A3FF;
        }
        50% {
            transform: scale(0.9);
            background: #80bff2;
        }
    }

</style>