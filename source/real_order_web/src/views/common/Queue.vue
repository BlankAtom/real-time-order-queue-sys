<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center" style="margin-bottom: 20px">
            <h1 style="size: auto;margin-top: 50px;font-size: 50px">{{ mName }}</h1>
            <!--                        <h1 style="size: auto;margin-top: 50px">{{ m_id }}</h1>-->
        </div>
        <div style="width: 300px;height:300px;margin: auto; " @click="queueup">
            <div class="box" v-if="show"/>
            <div v-else>
                <div class="normal"/>
            </div>
            <div class="center" style="padding-top: 30%">
                <label style="size: auto;font-size: 50px">{{ text }}</label>
            </div>
        </div>

        <div class="center" style="margin-top: 50px">
            <label style="size: auto;font-size: 50px">前面还有{{ qNub }}人</label>
        </div>
        <!--        <div class="center"-->
        <!--             style="border-radius: 100%;-->
        <!--             width: 300px;height: 300px;-->
        <!--            margin-top: 50px;-->
        <!--            margin-bottom: 50px;-->
        <!--            background-color: lightblue">-->
        <!--            <div style="height: max-content;width: max-content;margin: auto;padding-top: 30%    ">-->
        <!--                <label style="size: auto;font-size: 50px">{{text}}</label>-->
        <!--            </div>-->
        <!--        </div>-->

        <div class="center" style="margin-top: 80px">
            <el-button class="center" type="primary" style="size: 100px;font-size: 60px;background-color: #0c212b"
                       v-if="show"
                       @click="cancel">
                {{ btnText }}
            </el-button>
        </div>

    </div>


</template>
<script>
    import axios from "axios";
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
            const _this = this
            const {proxy} = getCurrentInstance();
            let m_id = this.m_id
            proxy.$axios.post('cus/onemerchant?m_id=' + m_id)
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
                mName: '俺是个店名',
                text: '点击排队',
                qNub: '很多',
                btnText: '',
                show: false,
                m_id: this.$route.params.mId,
                flag: true
            }
        },
        methods: {
            cancel(val) {
                this.btnText = '取消排队'
                this.text = '点击排队'
                this.show = !this.show
            },
            queueup(val) {
                if (!this.show)
                    this.show = !this.show
                /**  取排队号操作   **/
                this.text = '9856号'
            }
        }
    }

</script>

<style>
    .outer {
        background-image: url("../../assets/img/bluebackground.jpeg");
        background-size: 100% 100%;

        height: 100%;

        position: fixed;

        width: 100%
    }

    .center {
        width: max-content;
        margin: auto;
    }

    .normal {
        content: '';
        position: absolute;

        text-align: center;
        z-index: -1;
        width: 300px;
        height: 300px;
        background: lightblue;
        border-radius: 100%;
    }

    .box:before {
        content: '';
        position: absolute;

        text-align: center;
        z-index: -1;
        width: 300px;
        height: 300px;
        background: lightblue;
        border-radius: 100%;
        /*transform: rotate(45deg);*/
        -webkit-animation: box 3s infinite;
    }

    @-webkit-keyframes box {
        0% {
            /*top:50px;*/
            /*transform: rotate(90deg); */
        }
        20% {
            /*border-radius:100%;*/
        }
        50% {
            /*top:80px;*/
            transform: scale(0.9);
            /*border-bottom-right-radius:130%;*/
            /*border-top-left-radius:120% ;*/
            /*border-bottom-left-radius: 130%;*/
        }
        80% {
            /*border-radius:100%;*/
        }
        100% {
            /*top:50px;*/
            /*transform: rotate(0deg);*/
        }
    }

    .box:after {
        content: '';
        position: absolute;
        /*z-index:1;*/
        top: 192px;
        left: 50px;
        width: 100px;
        height: 6px;
        background: #eaeaea;
        border-radius: 100%;
        -webkit-animation: shadow 5s infinite;
    }

    @-webkit-keyframes shadow {
        0%, 100% {
            left: 50px;
            width: 100px;
            background: #eaeaea;
        }
        50% {
            top: 192px;
            left: 75px; /*讓陰影保持在原位*/
            width: 50px;
            height: 7px;
            background: #eee;
        }
    }
</style>