<template>
    <div class="list">
        <c-menu></c-menu>
        <h2 class="h2">车型列表</h2>
        <div class="handle-box">
            <el-input v-model="query.modelName" placeholder="车型" class="handle-select mr10"></el-input>
            <el-button type="primary" icon="el-icon-search" @click="getData">搜索</el-button>
        </div>
        <c-carList :car="car"></c-carList>
        <el-pagination
            background
            layout="total, prev, pager, next"
            :current-page="query.pageNum"
            :page-size="query.pageSize"
            :total="pageTotal"
            @current-change="handlePageChange"
        ></el-pagination>
        <c-footer></c-footer>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cCarList from "@/components/CarList"
import cFooter from "@/components/Footer"
import { carList } from '../api/index'
export default {
    name: "List",
    data() {
        return {
            car: [],
            query: {
                modelName: '',
                pageNum: 1,
                pageSize: 5
            },
            pageTotal: 0
        }
    },
    components: {
        cMenu,
        cCarList,
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
                    this.car = res.data.data;
                    this.pageTotal = res.data.count;
                } else {
                    this.$message.error(res.data.message);
                }
            }).catch((err) => {
                this.$message.error('请求失败');
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageNum', val);
            this.getData();
        }
    }
}
</script>

<style scoped>
.handle-select {
    width: 185px;
}
</style>