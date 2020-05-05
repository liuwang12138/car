<template>
    <div class="cards">
        <div class="card-item" v-for="(item, index) in carList" :key="index">
            <img class="card-img" :src="item.mainImageUrl">
            <div class="card-title">{{item.modelName}}</div>
            <div class="card-content">{{item.desc}}</div>
            <a class="card-a" @click="goDetail(item.id)">了解详情 > </a>
        </div>
    </div>
</template>

<script>
export default {
    name: "Card",
    props: ["carList"],
    methods: {
        goDetail(id) {
            this.$router.push({
                path: `/Detail/${id}`,
            })
        }
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.cards 
    display: flex;
    margin: 20px auto;
    width: 1000px;
    .card-item 
        flex: 1;
        width: 33.3%;
        .card-img
            max-width: 100%;
            width: 100% !important;
            margin: 0 auto 26px;
            display: block;
        .card-title
            margin-bottom: 9px;
            font-size: 22px;
            font-weight: bold;
            line-height: 26px;
            letter-spacing: .02em;
            color: #161618;
        .card-content
            margin: 0 10px;
            font-size: 15px;
            line-height: 22px;
            letter-spacing: .025em;
            color: #333;
        .card-a
            display: block;
            margin-top: 20px;
            line-height: 21px;
            font-size: 15px;
            color: #1c6bba;
</style>