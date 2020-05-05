<template>
    <div class="bAndGroup">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 业务及业务权限配置</el-breadcrumb-item>
                <el-breadcrumb-item> 业务与业务组配置</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <tree-table :data="data" :columns="columns" border/>
    </div>
</template>


<script>
import treeTable from './tree'
/* eslint-disable */
export default {
  components: { treeTable },
  data() {
    return {
      columns: [
        {
          text: '事件',
          value: 'name',
          width: 200
        },
        {
          text: 'ID',
          value: 'value'
        }
      ],
      data: [
    {
        index: 0,
        name: "code",
        value: "200"
    },
    {
        index: 1,
        name: "msg",
        value: "成功"
    },
    {
        index: 2,
        name: "data",
        value: "",
        child: [{
            index: 3,
            name: "data",
            value: "",
            child: [[{
                index: 4,
                name: "code",
                value: "000002"
            }, {
                index: 5,
                name: "name",
                value: "Ａ股指数"
            }],[{
                index: 4,
                name: "code",
                value: "000002"
            }, {
                index: 5,
                name: "name",
                value: "Ａ股指数"
            }]]
        }]
    }
      ]
    }
  }
}
</script>

