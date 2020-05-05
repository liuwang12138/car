<template>
    <div class="footer">
        <p class="footer-p">沃尔沃汽车在世界范围内销售，产品的售价、配置等因不同区域的市场而不同。网站中包含的图片、建议零售价格、配置仅供参考，与实际销售的产品存在不一致时，请以当地沃尔沃授权经销商实报售价、配置为准。</p>
        <p class="footer-p">©沃尔沃版权所有 </p>
        <br/>
    </div>
</template>

<script>
export default {
    name: "Footer",
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.footer 
    margin-top: 20px;
    background: black;
    .footer-p
        width: 800px;
        margin: 0 auto;
        font-size: 12px;
        line-height: 1.8;
        padding-top: 20px;
        letter-spacing: 2px;
        color: #fff;
</style>