<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center">
            <h1 style="size: auto;margin-top: 50px">{{ mName }}</h1>
        </div>
        <div class="center"
             style="border-radius: 100%;
             width: 300px;height: 300px;
            margin-top: 50px;
            margin-bottom: 50px;
            background-color: lightblue">
            <div style="height: max-content;width: max-content;margin: auto;padding-top: 30%    ">
                <label style="size: auto;font-size: 50px">{{text}}</label>
            </div>
        </div>
        <div class="center">
            <label style="size: auto;font-size: 50px">前面还有{{ qNub }}人</label>
        </div>
        <div class="center" style="margin-top: 80px">
            <el-button class="center" type="primary" style="size: 100px;font-size: 60px;background-color: #0c212b" @click="clickChange">
                {{ btnText }}
            </el-button>
        </div>

    </div>


</template>

<script>
    export default {
        data(){
            return{
                mName:'俺是个店名',
                text:'排队号',
                qNub:'很多',
                btnText:'有本事点一下'
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
    /*--主要的--*/
    .content {
        display: inline-block;
        vertical-align: middle;
    }

    .outer {
        background-image: url("../../assets/img/bluebackground.jpeg");
        background-size: 100% 100%;

        height: 100%;

        position: fixed;

        width: 100%
    }

    .flag {
        display: inline-block;
        vertical-align: middle;
        height: 100%;
        width: 0;
    }

    .center {
        width: max-content;
        margin: auto;
    }
</style>