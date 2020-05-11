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
                    <div>车长/车宽/车高：{{detail.carLength}}/{{detail.carWidth}}/{{detail.carHeight}}毫米</div>
                    <div>轴距：{{detail.wheelBase}}毫米</div>
                    <div>排量：{{detail.displacement}}毫升</div>
                    <div>0-100公里/时加速时间：{{detail.accelerationTime}}秒</div>
                    <div>综合耗油量：{{detail.oilConsumption}}升/公里</div>
                    <div>油箱容积：{{detail.fuelTankCapacity}}升</div>
                    <div>发布时间：{{detail.createTime}}</div>
                    <el-button @click="preOrderVisible = true">预定</el-button>
                </div>
            </div>
            <div class="dimg-wrap" v-for="(item, index) in detail.imageList" :key = index>
                <img :src="item"/>
            </div>
        </div>
        <!-- 提交弹出框 -->
        <el-dialog title="预约车型" :visible.sync="preOrderVisible" width="40%">
            <el-form ref="preOrderForm" :model="preOrderForm" label-width="80px">
                <el-form-item label="*客户姓名">
                    <el-input v-model="preOrderForm.fullName"></el-input>
                </el-form-item>
                <el-form-item label="*联系方式">
                    <el-input v-model="preOrderForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="电子邮件">
                    <el-input v-model="preOrderForm.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="preOrderVisible = false">取 消</el-button>
                <el-button type="primary" @click="order">确 定</el-button>
            </span>
        </el-dialog>
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
            detail: {},
            preOrderForm: {},
            preOrderVisible: false
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
            if (this.preOrderForm.fullName == null) {
                alert('用户姓名为空')
                return
            }
            if (this.preOrderForm.phone == null) {
                alert('手机号为空')
            }
            this.preOrderForm.carModelId = this.$route.params.id
            preOrder(this.preOrderForm).then(res => {
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