<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-group"></i> 售后服务
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
                <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                <el-table-column prop="modelName" label="车型名" align="center"></el-table-column>
                <el-table-column prop="fullName" label="客户姓名" align="center"></el-table-column>
                <el-table-column prop="phone" label="联系方式" align="center"></el-table-column>
                <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
                <el-table-column prop="serviceContent" label="客户留言" align="center"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" align="center" width="170px"></el-table-column>
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
import { getAfterSaleServiceList } from '../../api/index';
export default {
    name: 'basetable',
    inject:['reload'],
    data() {
        return {
            query: {
                startTime: '',
                endTime: '',
                pageNum: 1,
                pageSize: 10
            },
            tableData: [],
            editVisible: false,
            addVisible: false,
            pageTotal: 0,
            editForm: {},
            addForm: {},
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            console.log(this.query)
            getAfterSaleServiceList(this.query).then(res => {
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
