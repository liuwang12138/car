<template>
    <div class="mine">
        <c-menu></c-menu>
        <div class="mine-wrap">
            <div class="my-info">
                <div>昵称：{{username}}</div>
                <div>姓名：{{realName}}</div>
                <div>电话：{{phone}}</div>
                <el-button @click="editVisible = true">修改密码</el-button>
            </div>
            <div class="oh2">我的预购记录</div>
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
                        <el-button
                            type="text"
                            @click="makeAfterSaleVisible(scope.row)"
                        >申请售后</el-button>
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
        </div>
        <c-footer></c-footer>
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
        <!-- 申请售后弹出框 -->
        <el-dialog title="申请售后" :visible.sync="applyAfterSaleVisible" width="30%">
            <el-form ref="applyAfterSaleForm" :model="applyAfterSaleForm" label-width="90px">
                <el-form-item label="*客户姓名">
                    <el-input v-model="applyAfterSaleForm.fullName"></el-input>
                </el-form-item>
                <el-form-item label="*联系方式">
                    <el-input v-model="applyAfterSaleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="电子邮件">
                    <el-input v-model="applyAfterSaleForm.email"></el-input>
                </el-form-item>
                <el-form-item label="*申请内容">
                    <el-input type="textarea" :rows="5" v-model="applyAfterSaleForm.serviceContent"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="apply()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cFooter from "@/components/Footer"
import { myInfo, updatePassword, getMyPreOrderInfo, cancelPreOrder, insertAfterSaleService } from '../api/index';
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
            pageTotal: 0,
            applyAfterSaleForm: {},
            applyAfterSaleVisible: false,
            rowIdApply: ''
        }
    },
    components: {
        cMenu,
        cFooter
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
        apply() {
            if (this.applyAfterSaleForm.fullName == null) {
                alert("客户姓名为空")
            } else if (this.applyAfterSaleForm.phone == null) {
                alert("联系方式为空")
            } else if (this.applyAfterSaleForm.serviceContent == null) {
                alert("申请内容为空")
            }
            this.applyAfterSaleForm.preOrderId = this.rowIdApply
            insertAfterSaleService(this.applyAfterSaleForm).then(res => {
                console.log(res)
                if(res.data.code == 201) {
                    this.applyAfterSaleVisible = false
                    this.$message.success('申请成功！')
                } else {
                    this.$message.success(res.data.message)
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.params, 'pageNum', val);
            this.getMyPreOrder();
        },
        makeAfterSaleVisible(row) {
            this.applyAfterSaleVisible=true
            this.rowIdApply = row.preOrderId
        }
    }
}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.mine-wrap
    margin: 0 auto;
    width: 1080px;
    .my-info
        text-align: left;
        margin: 20px 0;
        div
            margin: 10px 0;
        a
            cursor: pointer;
    .oh2
        font-size: 24px;
        line-height: 32px;
        font-weight: bold;
        text-align: left;
    .table 
        margin: 20px 0;
        font-size: 14px;
</style>