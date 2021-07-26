<template>
    <div class="cm-div">
        <h1>当个用户</h1>
        <el-upload action="http://localhost:8081/dish/upload/pic" class="avatar-uploader"
                   :on-success="handleSuccess" :before-upload="beforeAvatarUpload"
                    >
            <img v-if="img" :src="img" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-button @click="handleUpload">Hello</el-button>
    </div>
</template>

<script>
import axios from "../../utils/axios";
import qs from "qs";

export default {
    name: "ChooseMerchant.vue",
    setup() {

    },
    data() {
        return {
            img: ""
        }
    },
    methods: {
        handleUpload() {
            console.log(this.img);
            axios.post("http://localhost:8081/dish/upload/pic", qs.stringify( this.img)).then(res => {
                console.log(res)
            }).catch(err => {
                console.log(err)
            })
        },
        handleSuccess(res, file) {
            this.img = URL.createObjectURL(file.raw)
            console.log(this.img)

        },
        beforeAvatarUpload(file) {
            const isJPEG = file.type === 'image/jpeg';
            const isPng = file.type === 'image/png'
            const isJpg = file.type === 'image/jpg'
            const isLt2M = file.size / 1024 / 1024 < 5;

            if (!isJPEG && !isPng && !isJpg) {
                this.$message.error('上传头像图片只能是 PNG/JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 5MB!');
            }
            return (isJPEG || isJpg || isPng) && isLt2M;
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>