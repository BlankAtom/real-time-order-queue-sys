<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center">
            <h1 style="size: auto;margin-top: 50px">{{ mName }}</h1>
        </div >
        <div style="width: 300px; margin: auto; horiz-align: center">

            <div class="box" >
            </div>
        </div>
        <div class="center">
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
            <el-button class="center" type="primary" style="size: 100px;font-size: 60px;background-color: #0c212b" @click="clickChange">
                {{ btnText }}
            </el-button>
            <h1 hidden >{{$route.params.mId}}</h1>

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
        data(){
            return{
                mName:'俺是个店名',
                text:'排队号',
                qNub:'很多',
                btnText:'有本事点一下',
                show:true
            }
        },
        methods:{
            clickChange(val){
                this.btnText='你还真点啊'
                console.log(this.btnText)
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

    .box:before{
        content:'';
        position:absolute;
        /*z-index:2;*/
        /*text-align: center;*/
        /*vertical-align: middle;*/
        top:60px;
        /*left:50px;*/
        width:300px;
        height:300px;
        background:lightblue;
        border-radius:50%;
        /*transform: rotate(45deg);*/
        -webkit-animation:box 3s infinite ;
    }
    @-webkit-keyframes box{
        0%{
            /*top:50px;*/
            /*transform: rotate(90deg); */
        }
        20%{
            /*border-radius:100%;*/
        }
        50%{
            /*top:80px;*/
            transform: scale(0.9);
            /*border-bottom-right-radius:130%;*/
            /*border-top-left-radius:120% ;*/
            /*border-bottom-left-radius: 130%;*/
        }
        80%{
            /*border-radius:100%;*/
        }
        100%{
            /*top:50px;*/
            /*transform: rotate(0deg);*/
        }
    }
    .box:after{
        content:'';
        position:absolute;
        /*z-index:1;*/
        top:192px;
        left:50px;
        width:100px;
        height:6px;
        background:#eaeaea;
        border-radius:100%;
        -webkit-animation:shadow 5s infinite ;
    }
    @-webkit-keyframes shadow{
        0%,100%{
            left:50px;
            width:100px;
            background:#eaeaea;
        }
        50%{
            top:192px;
            left:75px;   /*讓陰影保持在原位*/
            width:50px;
            height:7px;
            background:#eee;
        }
    }
</style>