<template>
    <div class="home">
        <c-menu></c-menu>
        <c-banner></c-banner>
        <h2 class="h2">源自瑞典 北欧豪华</h2>
        <c-card :carList="carList"></c-card>
        <div class="img-wrapper">
            <img class="img-item1" src="@/assets/img1.png" alt="">
            <div class="img-item2">
                <img src="@/assets/s902.jpeg" alt="">
                <img src="@/assets/6.jpg" alt="">
            </div>
            <div class="img-item3">
                <img src="@/assets/img4.jpg" alt="">
                <img src="@/assets/s901.jpeg" alt="">
            </div>
        </div>
        <c-footer></c-footer>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cBanner from "@/components/Banner"
import cCard from "@/components/Card"
import cFooter from "@/components/Footer"
import { carList } from '../api/index'
export default {
    name: "Home",
    data() {
        return {
            carList: [],
            query: {
                modelName: '',
                pageNum: 1,
                pageSize: 3
            },
        }
    },
    components: {
        cMenu,
        cBanner,
        cCard,
        cFooter
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            carList(this.query).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.carList = res.data.data;
                } else {
                    this.$message.error(res.data.message);
                }
            }).catch((err) => {
                this.$message.error('请求失败');
            });
        },
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.img-wrapper
    display: flex;
    width: 100%;
    margin: 40px 0 20px;
    .img-item1
        flex: 2;
        width: 48%;
        padding-left: 10px;
    .img-item2
        flex: 1;
        img
            width: 98%;
            padding-left: 5px;
            display: inline;
    .img-item3
        flex: 1;
        img
            width: 98%;
            margin-left: 5px;
            display: inline;
</style>