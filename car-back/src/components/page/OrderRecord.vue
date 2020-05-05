<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-copy"></i> 销售记录管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-date-picker type="datetime" placeholder="开始时间" v-model="query.startTime" value-format="yyyy-MM-dd HH:mm:ss" class="handle-select mr10"></el-date-picker>
                <el-date-picker type="datetime" placeholder="结束时间" v-model="query.endTime" value-format="yyyy-MM-dd HH:mm:ss" class="handle-select mr10"></el-date-picker>
                <el-button type="primary" icon="el-icon-search" @click="getData">搜索</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                header-cell-class-name="table-header"
            >
                <el-table-column prop="preOrderId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="carModelName" label="车型" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                <el-table-column prop="preOrderTime" label="下单时间" align="center"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageNum"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import { orderList } from '../../api/index';
export default {
    name: 'basetable',
    data() {
        return {
            query: {
                startTime: '',
                endTime: '',
                pageNum: 1,
                pageSize: 2
            },
            tableData: [],
            pageTotal: 0,
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            orderList(this.query).then(res => {
                console.log(res)
                if(res.data.code == 201) {
                    console.log(res.data);
                    this.tableData = res.data.data;
                    this.pageTotal = res.data.count;
                } else {
                    console.log(1);
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageNum', val);
            this.getData();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 185px;
}

.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
