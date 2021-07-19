<template>
<!--    <el-card shadow="never" class="index">-->
<!--        <template #header>-->
<!--            <div class="card_header">-->
<!--                <b>富文本编辑器</b>-->
<!--            </div>-->
<!--        </template>-->
<!--        &lt;!&ndash; 富文本 &ndash;&gt;-->
<!--        <WEditor :defaultText="defaultText" @richHtml="getRichHtml" />-->
<!--    </el-card>-->

    <div>
        <el-table @row-click="clickInto"
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    min-width="30%"
                    label="图片"
                    align="center"
                    >
                <img src="../../assets/img/admin.png" style="height: 80px;">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="商家名称"
                    align="center"
                    >
            </el-table-column>
            <el-table-column hidden
                    prop="mId"
                    align="center"
            >
            </el-table-column>
        </el-table>
<!--    <p v-if="isPC">1111111</p>-->
<!--    <p v-else>1111111</p>-->
<!--      <p>{{isPC}}</p>-->
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import WEditor from "../../components/WEditor.vue";

export default {
    components: { WEditor },
    setup() {
        const defaultText = ref("");
        const richText = ref("");
        onMounted(() => {
            // 初始化数据
            defaultText.value = "<h1>Editor</h1>";
        });
        const getRichHtml = nv => {
            richText.value = nv;
        };

        return { defaultText, getRichHtml };
    },
  data(){
      return{
        isPC:document.documentElement.clientWidth > 640,
          tableData: [{
              pic: '2016-05-02',
              name: '王小虎',
              mId: '上海市普陀区金沙江路 1518 弄'
          }, {
              pic: '2016-05-04',
              name: '461842154889787987978978978978989789',
              mId: '上海市普陀区金沙江路 1517 弄'
          }, {
              pic: '2016-05-01',
              name: '王小虎',
              mId: '上海市普陀区金沙江路 1519 弄'
          }, {
              pic: '2016-05-03',
              name: '王小虎',
              mId: '上海市普陀区金沙江路 1516 弄'
          }]
      }
  },
  methods:{
      clickInto(val){
        let thisRowData=this
          thisRowData =val
          let mId=val.mId
          this.lineUp(mId)
          console.log(val.pic)
      },
      lineUp(mId){
          console.log(mId)
          this.$router.push("/queue/"+mId)
      }
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
