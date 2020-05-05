<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 库存管理
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
                >添加车型</el-button>
                <el-input v-model="query.modelName" placeholder="车型" class="handle-select mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                header-cell-class-name="table-header"
            >
                <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="modelName" label="车型"></el-table-column>
                <el-table-column prop="stock" label="库存量" align="center"></el-table-column>
                <el-table-column prop="mainImageUrl" label="封面" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.mainImageUrl"
                            :preview-src-list="[scope.row.thumb]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <!-- <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button> -->
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
        <el-dialog title="添加车型" :visible.sync="addVisible" width="40%">
            <el-form ref="addForm" :model="addForm" label-width="70px">
                <el-form-item label="车型">
                    <el-input v-model="addForm.modelName"></el-input>
                </el-form-item>
                <el-form-item label="库存量">
                    <el-input v-model="addForm.stock"></el-input>
                </el-form-item>
                <el-form-item label="价格">
                    <el-input v-model="addForm.price"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="addForm.desc"></el-input>
                </el-form-item>
                <el-form-item label="主图片">
                    <el-upload
                        :action="url"
                        list-type="picture-card"
                        :limit="1"
                        accept="jpg, png"
                        :file-list="fileList"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        :before-upload="beforeImageUpload"
                        name="multipartFile"
                        :on-success="mainImageSuccess"
                        :on-exceed="imageExceed"
                        :auto-upload="true"
                    >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt />
                    </el-dialog>
                </el-form-item>
                <el-form-item label="详情图片">
                    <el-upload
                        :action="url"
                        list-type="picture-card"
                        :limit="9"
                        accept="jpg, png"
                        :file-list="fileList"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        :before-upload="beforeImageUpload"
                        name="multipartFile"
                        :on-success="detailImageSuccess"
                        :on-exceed="imageExceed"
                        :auto-upload="true"
                    >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt />
                    </el-dialog>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="addCar">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="editForm" :model="editForm" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="editForm.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="editForm.address"></el-input>
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
import { baseUrl, carModel, uploadFile, carModelInsert, carModelDel } from '../../api/index';
export default {
    name: 'basetable',
    inject:['reload'],
    data() {
        return {
            url: baseUrl + 'file/upload/',
            query: {
                modelName: '',
                pageNum: 1,
                pageSize: 10
            },
            tableData: [],
            addVisible: false,
            editVisible: false,
            pageTotal: 0,
            editForm: {},
            addForm: {},

            // upload image
            fileList: [],
            dialogImageUrl: "",
            dialogVisible: false,
            mailImageFileUrl: "",
            //detailImageFileList: [],
            detailImageFileList: "",
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            carModel(this.query).then(res => {
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
        addCar() {
            this.addForm.mainImageUrl = this.mailImageFileUrl
            this.addForm.detailImageList = this.detailImageFileList
            console.log(this.addForm)
            carModelInsert(this.addForm).then(res => {
                console.log()
                if(res.data.code == 201) {
                    this.$message.success("插入成功！")
                } else if (res.data.code == 203) {
                    this.$message.error("您没有登陆，请先登陆！")
                } else {
                    this.$message.error(res.data.message)
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        saveEdit() {

        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                carModelDel({carModelId: row.id}).then(res => {
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
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageNum', val);
            this.getData();
        },


        // upload image
        //图片上传之前
        beforeImageUpload(file, fileList) {
            console.log(file)
            var testmsg=file.name.substring(file.name.lastIndexOf('.')+1) 
            const isJpg = testmsg === 'jpg' || testmsg === 'png'
            if (!isJpg) {
                this.$message.error('上传图片只能是 jpg 或 png 格式!')
                return false
            }
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!')
                return false
            }
            // return false // (返回false不会自动上传)
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url
            this.dialogVisible = true
        },
        handleRemove(file, fileList) {
            for(var i = 0; i < this.fileList.length; i++){
                if(this.fileList[i].url === file.url){
                deleteImageReport(this.fileList[i].id).then(res =>{
                    this.$message.success('删除图片成功')
                })
                this.fileList.splice(i, 1)
                }
            }
        },
        mainImageSuccess(file) {
            if (file.code == 201) {
                this.mailImageFileUrl = file.data.imageUrl
            } else {
                this.$message.error('上传失败')
            }
        },
        detailImageSuccess(file) {
            if (file.code == 201) {
                let img = file.data.imageUrl
                this.detailImageFileList += (img + ",")
            } else {
                this.$message.error('上传失败')
            }
        },
        imageExceed() {
            this.$message.error("图片数量达到上限！")
        },
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
