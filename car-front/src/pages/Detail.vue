<template>
    <div class="detail">
        <c-menu></c-menu>
        <div class="dback" @click="back"> 《 返回</div>
        <div class="detail-wrap">
            <div class="detail-top">
                <img :src="detail.mainImageUrl" alt="">
                <div class="car-right">
                    <div>车型：{{detail.modelName}}</div>
                    <div>价格：{{detail.price}}</div>
                    <div>库存：{{detail.stock}}</div>
                    <div>简介：{{detail.desc}}</div>
                    <div>发布时间：{{detail.createTime}}</div>
                    <el-button @click="order">预定</el-button>
                </div>
            </div>
            <div class="dimg-wrap" v-for="(item, index) in detail.imageList" :key = index>
                <img :src="item"/>
            </div>
        </div>
        <c-footer></c-footer>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cFooter from "@/components/Footer"
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
        cFooter
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
        },
        back() {
            this.$router.go(-1);
        }
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.dback
    margin: 20px auto;
    width: 1080px;
    text-align: left;
.detail-wrap
    margin: 20px auto;
    width: 1080px;
    text-align: left;
    .detail-top
        display: flex;
        img 
            flex: 1;
            width: 50%;
        .car-right
            flex: 1;
            div
                margin: 10px;
                font-size: 18px;
                color: #000;
                text-align: left;
    .dimg-wrap
        margin: 40px 0;
        img
            margin: 10px 0;
            width 100%;
</style>