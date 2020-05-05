<template>
  <el-carousel height="400px" class="banner-wrap">
    <el-carousel-item v-for="(item, index) in bannerList" :key="index">
      <img class="banner" :src="item.src" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
    name: "Banner",
    data() {
        return {
            bannerList: [
                {
                    src: require("@/assets/slide_pc1.jpg"),
                    title: "首页"
                },
                {
                    src: require("@/assets/slide_pc2.png"),
                    title: "车型列表"
                },
                {
                    src: require("@/assets/slide_pc3.jpg"),
                    title: "地图"
                }
            ]
        };
    },
}
</script>

<style>
  .banner-wrap {
      position: relative;
  }
  .banner {
      position: absolute;
      left: 0;
      bottom: 0;
      width: 100%;
  }
</style>