<template>
    <div class="outer" style="height: 100%;width: 100%">
        <div class="center">
            <h1 style="size: auto;margin-top: 50px">俺是个店名</h1>
        </div>
        <div class="center"
             style="border-radius: 100%;
             width: 300px;height: 300px;
            margin-top: 50px;
            margin-bottom: 50px;
            background-color: lightblue">
            <div style="height: max-content;width: max-content;margin: auto;padding-top: 30%    ">
                <label style="size: auto;font-size: 50px">太难了</label>
            </div>
        </div>
        <div class="center">
            <label style="size: auto;font-size: 50px">前面还有好多人</label>
        </div>
        <div class="center" style="margin-top: 80px">
            <el-button class="center" type="primary" style="size: 100px;font-size: 60px;background-color: #0c212b">
                他妈有本事点一下
            </el-button>
        </div>

    </div>


</template>

<script>

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