<template>
    <div class="business">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 业务及业务权限配置</el-breadcrumb-item>
                <el-breadcrumb-item> 业务配置</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <v-table :tableData="tableData" :tableKey="tableKey"></v-table>
        </div>
    </div>
</template>

<script>
    import vTable from '../../components/table'
    import {user} from '../../assets/api'
    export default {
        name: 'business',
        data() {
            return {
                tableData: [],
                tableKey: [{
                    name: 'id',
                    value: 'id'
                }, {
                    name: '用户名称',
                    value: 'interface_address'
                }, {
                    name: '创建时间',
                    value: 'description'
                }]
            }
        },
        components: {
            vTable
        },
        mounted() {
            this.getUser();
        },
        methods: {
            getUser() {
                
                user().then((res) => {
                    console.log(res);
                    this.tableData = res;
                })
            }
        }
    }
</script>

<style scoped>
    
</style>
