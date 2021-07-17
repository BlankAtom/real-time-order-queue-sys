<template>
    <el-card shadow="never" class="index">
        <template #header>
            <div class="card_header">
                <b>🍉西瓜播放器</b>
            </div>
        </template>
        <div id="xg"></div>
    </el-card>
</template>

<script>
import { onMounted, onBeforeUnmount, getCurrentInstance, ref } from "vue";
import Player from "xgplayer";

export default {
    setup() {
        const { proxy } = getCurrentInstance();

        let player;
        onMounted(() => {
            initPlayer();
        });

        onBeforeUnmount(() => {
            player.destroy();
            player = null;
        });

        const initPlayer = () => {
            player = new Player({
                id: "xg",
                url: "https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4",
                poster: "https://img03.sogoucdn.com/app/a/07/f13b5c3830f02b6db698a2ae43ff6a67",
                fitVideoSize: "auto",
                fluid: true /* 流式布局 */,
                // download: true /* 视频下载 */
                // pip: true /* 画中画 */,
                // errorTips: `请<span>刷新页面</span>试试` /* 自定义错误提示 */,
                lang: "zh-cn"
            });
        };

        return {};
    }
};
</script>

<style lang="scss" scoped>
.card_header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
</style>
