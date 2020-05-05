<template>
    <div class="future">
        <c-menu></c-menu>
        <div class="video-player">
            <video src="@/assets/video.mp4" preload="auto" autoplay="autoplay" poster="@/assets/stop.png" ></video>
        </div>
        <h2 class="h2">我们正在重新定义个人移动出行方式</h2>
        <div class="vContent">我们希望为您提供个性化、可持续、安全的移动出行解决方案。为此，我们将您的需求放在首位——以最切实可行的技术服务于您，并进行创新，融合新技术，从而提供新的体验。</div>
        <div class="vContent">我们致力于实现以下愿景：</div>
        <div class="vContent">没有人因驾乘新款沃尔沃汽车而发生重大伤亡事故。我们希望到2025年，驾乘新款沃尔沃汽车将每年为车主提供一周高质量的驾乘体验。到2025年，我们生产和销售的汽车中约有一半为电动汽车。</div>
        <div class="vContent">沃尔沃汽车，为更好的生活而创新。我们关注的是您和您的世界。</div>
        <br/>
        <c-footer></c-footer>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cFooter from "@/components/Footer"
export default {
    name: "Future",
    components: {
        cMenu,
        cFooter
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.future
    .video-player
        video
            width:100%;
            height: 100%;
    .vContent
        width: 660px;
        margin: 20px auto;
        color: #000;
</style>