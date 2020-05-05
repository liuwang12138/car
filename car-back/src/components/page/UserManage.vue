<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-group"></i> 客户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-lx-add"
                    class="handle-del mr10"
                    @click="addVisible = true"
                >添加用户</el-button>
                <el-input v-model="query.username" placeholder="用户名" class="handle-select mr10"></el-input>
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
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="phone" label="联系方式"></el-table-column>
                <el-table-column prop="registerTime" label="注册时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.row)"
                        >修改</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.row)"
                        >删除</el-button>
                    </template>
                </el-table-column>
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
        <!-- 添加弹出框 -->
        <el-dialog title="添加用户" :visible.sync="addVisible" width="40%">
            <el-form ref="addForm" :model="addForm" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="addForm.password"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="addForm.realName"></el-input>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input v-model="addForm.phone"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
            <el-form ref="editForm" :model="editForm" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="editForm.password"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="editForm.realName"></el-input>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input v-model="editForm.phone"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { userList, userInsert, userDelete, getUser, userUpdate } from '../../api/index';
export default {
    name: 'basetable',
    inject:['reload'],
    data() {
        return {
            query: {
                username: '',
                startTime: '',
                endTime: '',
                pageNum: 1,
                pageSize: 2
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
            userList(this.query).then(res => {
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
        addUser() {
            userInsert(this.addForm).then(res => {
                console.log(res);
                if(res.data.code == 201) {
                    console.log(res);
                    this.$message.success("添加成功");
                    this.addVisible = false;
                    this.reload();
                } else {
                    this.$message.error("添加失败");
                }
            }).catch((err) => {
                this.$message.error("添加失败");
            });
        },
        // 删除操作
        handleDelete(row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                userDelete({userId: row.id}).then(res => {
                    if(res.data.code == 201) {
                        console.log(res);
                        this.$message.success("删除成功");
                        this.reload();
                    } else {
                        this.$message.error("删除失败");
                    }
                }).catch((err) => {
                    this.$message.error("删除失败");
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        },
        // 根据id获取信息
        handleEdit(row) {
            getUser({userId: row.id}).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.editForm = res.data.data;
                    this.editVisible = true;
                } else {
                    this.$message.error("请求失败");
                }
            }).catch((err) => {
                this.$message.error("请求失败");
            });
        },
        // 保存编辑
        saveEdit() {
            userUpdate(this.editForm).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.editVisible = false;
                    this.$message.success(`修改成功`);
                    this.reload();
                } else {
                    this.$message.error("请求失败");
                }
            }).catch((err) => {
                this.$message.error("请求失败");
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
