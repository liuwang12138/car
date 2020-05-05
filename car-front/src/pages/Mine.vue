<template>
    <div class="mine">
        <c-menu></c-menu>
        <div class="my-info">
            <div>昵称：{{username}}</div>
            <div>姓名：{{realName}}</div>
            <div>电话：{{phone}}</div>
            <a @click="editVisible = true">修改密码</a>
        </div>
        <el-table :data="tableData" border class="table" header-cell-class-name="table-header">
            <el-table-column prop="preOrderId" label="ID" width="55" align="center"></el-table-column>
            <el-table-column prop="carModelName" label="车型" align="center"></el-table-column>
            <el-table-column prop="username" label="用户名" align="center"></el-table-column>
            <el-table-column prop="preOrderTime" label="下单时间" align="center"></el-table-column>
            <el-table-column label="操作" width="180" align="center">
                <template slot-scope="scope">
                    <el-button
                        type="text"
                        @click="cancel(scope.row)"
                    >取消预订</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="total, prev, pager, next"
            :current-page="params.pageNum"
            :page-size="params.pageSize"
            :total="pageTotal"
            @current-change="handlePageChange"
        ></el-pagination>
        <!-- 修改密码弹出框 -->
        <el-dialog title="修改密码" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="原密码">
                    <el-input v-model="form.oldPassword"></el-input>
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input v-model="form.newPassword"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="update">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import { myInfo, updatePassword, getMyPreOrderInfo, cancelPreOrder } from '../api/index';
export default {
    name: "Mine",
    data() {
        return {
            username: '',
            realName: '',
            phone: '',
            editVisible: false,
            form: {},
            params: {
                pageNum: 1,
                pageSize: 5
            },
            tableData: [],
            pageTotal: 0
        }
    },
    components: {
        cMenu,
    },
    created() {
        this.getData();
        this.getMyPreOrder();
    },
    methods: {
        getData() {
            myInfo().then(res => {
                console.log(res);
                if(res.data.code == 201) {
                    console.log(res);
                    this.username = res.data.data.username;
                    this.realName = res.data.data.realName;
                    this.phone = res.data.data.phone;
                } else {
                    this.$message.error(res.data.message);
                }
            }).catch((err) => {
                this.$message.error('请求失败');
            });
        },
        update() {
            updatePassword(this.form).then(res => {
                console.log(res);
                if(res.data.code == 201) {
                    console.log(res);
                    this.$message.success('修改成功');
                    this.editVisible = false;
                } else {
                    this.$message.error(res.data.message);
                    this.editVisible = false;
                }
            }).catch((err) => {
                this.$message.error('请求失败');
                this.editVisible = false;
            });
        },
        getMyPreOrder() {
            getMyPreOrderInfo(this.params).then(res => {
                console.log(res)
                if(res.data.code == 201) {
                    console.log(res.data);
                    this.tableData = res.data.data;
                    this.pageTotal = res.data.count;
                } else {
                    this.$message.success(res.data.message);
                    console.log(1);
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        cancel(row) {
            cancelPreOrder({preOrderId: row.preOrderId}).then(res => {
                console.log(res)
                if(res.data.code == 201) {
                    console.log(res.data);
                    this.$message.success('取消成功');
                    this.getMyPreOrder();
                } else {
                    this.$message.success(res.data.message);
                    console.log(1);
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.params, 'pageNum', val);
            this.getMyPreOrder();
        }
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
    .my-info
        margin: 0 auto;
        width: 1080px;
        text-align: left;
        a
            cursor: pointer;
    .table {
        width: 100%;
        font-size: 14px;
    }
</style>