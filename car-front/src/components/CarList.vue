<template>
    <div class="car-list">
        <div class="car-item" v-for="(item, index) in car" :key="index">
            <img :src="item.mainImageUrl" alt="">
            <div class="car-right">
                <p>车型：{{item.modelName}}</p>
                <p>价格：{{item.price}}</p>
                <p>库存：{{item.stock}}</p>
                <p>上线日期：{{item.createTime}}</p>
                <a class="car-a" @click="goDetail(item.id)">查看详情 > </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "CarList",
    props: ["car"],
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
.car-list
    margin: 0 auto;
    width: 1080px;
    .car-item
        margin: 20px;
        display: flex;
        img 
            flex: 1;
            width: 50%;
        .car-right
            flex: 1;
            p
                margin: 10px;
                font-size: 18px;
                color: #000;
                text-align: left;
        .car-a
            display: block;
            margin: 10px;
            line-height: 21px;
            font-size: 15px;
            color: #1c6bba;
            text-align: left;
</style>