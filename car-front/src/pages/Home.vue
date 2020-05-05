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
export default {
    name: "Home",
    data() {
        return {
            carList: [
                {
                    type: "XC40",
                    desc: "沃尔沃XC40以唯我不同的北欧设计诠释时尚格调，以沃尔沃家族DNA彰显豪华气质。人性化设计与智能科技带来享我所想的随心所驭。",
                    url: require("@/assets/xc401.jpg")
                },
                {
                    type: "全新S60",
                    desc: "沃尔沃全新S60北欧豪华中型轿车，健康随行、安全随心、动感随型，护佑您和家人出行的每一步。",
                    url: require("@/assets/s60.jpg")
                },
                {
                    type: "XC90",
                    desc: "沃尔沃XC90承袭北欧经典，至简美学诠释豪华品味，匠心造诣雕琢大器空间，前瞻创新洞见趋势。驰骋天地，执掌未来格局。",
                    url: require("@/assets/xc90-20190903-1.jpg")
                },
            ]
        }
    },
    components: {
        cMenu,
        cBanner,
        cCard,
        cFooter
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