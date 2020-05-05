<template>
    <div class="table">
        <el-table :data="tableData" border stripe class="table">
            <el-table-column label="序号" type="index" width="65" align="center"></el-table-column>
            <el-table-column v-for="(item,key) in tableKey" :key="key" :prop="item.value" :label="item.name" sortable align="center">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: 'Table',
        props: ['tableData', 'tableKey']
    }
</script>

<style scoped>
    .table {
        font-size: 14px;
    }
</style>