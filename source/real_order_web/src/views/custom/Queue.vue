<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center" style="margin-bottom: 20px">
            <h1 v-text="mName"></h1>
        </div>

<!--        圆形按钮+中心文字内容-->
        <div class="round-box"   >
<!--            <div class="round-anime-playing" v-if="show">-->
<!--                <div class="round-queue-num" v-text="text"/>-->
<!--            </div>-->
            <div class="round-anime-normal" @click="startQueue" >
                <div class="round-queue-num" v-text="text"/>
            </div>
        </div>

<!--        <div style="margin-top: 150px">-->
<!--            <label style="color: black;size: auto;font-size: 40px" v-if="show">前方共有<span style="color: darkgray;" v-text="qNub"></span>人排队</label>-->
<!--        </div>-->

<!--        <div style="margin-top: 80px">-->
<!--            <el-button type="primary" class="btn-cancel-queue"-->
<!--                       v-if="show"-->
<!--                       @click="cancel"-->
<!--                       v-text="btnText">-->
<!--            </el-button>-->
<!--        </div>-->

    </div>


</template>
<script>
    import {getCurrentInstance} from "vue";

    export default {
        // setup(){
        //     const {proxy} = getCurrentInstance();
        //     proxy.$axios.post('cus/onemerchant',proxy.$qs.stringify($route.params.mId))
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
        // created() {
        //     const {proxy} = getCurrentInstance();
        //     proxy.$axios.post('cus/onemerchant',proxy.$qs.stringify($route.params.mId))
        //         .then(function (response){
        //             this.test=response,date()
        //             console.log("6854")
        //             console.log(response.data)
        //         })
        //         .catch(function (error){
        //             console.log(error)
        //         })
        // },
        created() {
            const {proxy} = getCurrentInstance();
            let m_id = this.m_id
            proxy.$axios.post('merchant/onemerchant?m_id=' + m_id)
                .then((response) => {
                    this.data = response.data
                    console.log("1111" + response.data)
                    console.log(this.data)
                    let {m_id, m_name, m_phone} = response.data
                    this.mName = m_name
                    console.log(m_id)
                    // this.mName=m.m_name
                }).catch((error) => {
                console.log(error)
            })
        },
        data() {
            return {
                data: [],
                mName: '',
                text: '点击排队',
                qNub: '0',
                m_id: this.$route.params.mId,
                flag: true
            }
        },
        methods: {
            startQueue(val) {
                /** 选择排队并跳转 **/
                this.$axios.post('merchant/lineUp?m_id=' + this.m_id+"&c_id="+window.sessionStorage.getItem('cid'))
                    .then((response) => {
                    }).catch((error) => {
                  console.log(error)
                })
                this.$alert('排队成功', '提示', {
                    confirmButtonText: '确定',
                    type: 'success'
                }).then(() => {
                    this.$router.push("/myqueue")
                });
                // alert("排队成功")
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