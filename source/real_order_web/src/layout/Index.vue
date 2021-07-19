<template>
    <el-container class="layout">
        <Aside />
        <el-container>
            <Header />
            <div class="app_wrap">
                <el-main>
                    <router-view />
                </el-main>
                <!-- footer -->
                <el-footer height="48px">
                    Copyright &copy; 2021
                    <el-link href="https://www.cnblogs.com/zxk5211" target="_blank">下落香樟树</el-link> ·
                    <el-link href="https://juejin.cn/user/184373682901309" target="_blank">掘金社区</el-link> ·
                    <el-link href="https://blog.csdn.net/SK_21" target="_blank">CSDN</el-link>
                </el-footer>
                <!-- backtop -->
                <el-backtop target=".app_wrap"></el-backtop>
            </div>
        </el-container>
    </el-container>
</template>

<script>
import Aside from "./Aside.vue";
import Header from "./Header.vue";

export default {
    components: { Aside, Header },
    setup() {}
};
</script>

<style lang="scss" scoped>
@mixin zdy_scrollbar {
    scrollbar-width: thin;
    scrollbar-color: #e5e5e5 #f7f7f9;
    &::-webkit-scrollbar {
        width: 10px;
        height: 8px;
        background-color: #f5f5f5;
    }
    &::-webkit-scrollbar-thumb {
        background-color: #ddd;
        border-radius: 10px;
    }
    &::-webkit-scrollbar-thumb:hover {
        background-color: #409eff;
        border-radius: 10px;
    }
}

.layout {
    box-sizing: border-box;
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    background-color: #fff;
    .el-container {
        height: 100vh;
        overflow: hidden;
        display: flex;
        display: -webkit-flex;
        flex-direction: column;

        .app_wrap {
            flex: 1;
            height: auto;
            background-color: #f3f6f8;
            overflow: hidden;
            overflow-y: auto;
            @include zdy_scrollbar;
            .el-main {
                padding: 16px;
                min-height: calc(100vh - 105px);
                height: auto;
            }

            .el-footer {
                line-height: 48px;
                font-size: 14px;
                color: rgba(0, 0, 0, 0.5);
                text-align: center;
                overflow: hidden;
                user-select: none;
            }
        }
    }
}
</style>
