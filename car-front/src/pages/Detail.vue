<template>
    <div class="detail">
        <c-menu></c-menu>
        <!-- <div @click="back">返回</div> -->
        <div>
            <div>车型：{{detail.modelName}}</div>
            <div>价格{{detail.stock}}</div>
            <div>简介：{{detail.desc}}</div>
            <div>发布时间：{{detail.createTime}}</div>
            <div @click="order">预定</div>
            <div v-for="(item, index) in detail.imageList" :key = index>
                <div>{{item}}</div>
            </div>
        </div>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import { carDetail, preOrder } from '../api/index'
export default {
    name: "Detail",
    data() {
        return {
            detail: {}
        }
    },
    components: {
        cMenu,
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            carDetail({modelId: this.$route.params.id}).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.detail = res.data.data;
                    this.pageTotal = res.data.count;
                } else {
                    this.$message.error(res.data.message);
                }
            }).catch((err) => {
                this.$message.error('请求失败');
            });
        },
        order() {
            preOrder({carModelId: this.$route.params.id}).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.$message.success('预定成功');
                } else {
                    this.$message.error(res.data.message);
                }
            }).catch((err) => {
                this.$message.error('请求失败');
            });
        }
        // back() {
        //     this.$router.push({
        //         path: 'List',
        //     })
        // }
    }
}
</script>

<style scoped>

</style>