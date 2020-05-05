export default {
    menu: [
        {
            icon: 'el-icon-lx-home',
            index: 'loadCurrent',
            title: '装载当前配置'
        },
        {
            icon: 'el-icon-lx-cascades',
            index: '1',
            title: '用户权限配置',
            subs: [
                {
                    index: 'user',
                    title: '用户配置',
                },
                {
                    index: 'group',
                    title: '组配置',
                }, 
                {
                    index: 'userAndGroup',
                    title: '用户与用户组配置',
                }
            ]
        },
        {
            icon: 'el-icon-lx-copy',
            index: '2',
            title: '业务及业务权限配置',
            subs: [
                {
                    index: 'business',
                    title: '业务配置',
                },
                {
                    index: 'bGroup',
                    title: '组配置',
                }, 
                {
                    index: 'bAndGroup',
                    title: '业务与业务组配置',
                }
            ]
        },
        {
            icon: 'el-icon-lx-calendar',
            index: '3',
            title: '接口信息配置',
            subs: [
                {
                    index: 'port',
                    title: '接口配置'
                },
                {
                    index: 'system',
                    title: '系统参数配置'
                },
                {
                    index: 'table',
                    title: '表配置'
                },
                {
                    index: 'move',
                    title: '迁移配置'
                }
            ]
        },
        {
            icon: 'el-icon-lx-emoji',
            index: 'userBusGroup',
            title: '用户业务组关系配置'
        },
        {
            icon: 'el-icon-pie-chart',
            index: 'codeImport',
            title: '脚本导出'
        },
        {
            icon: 'el-icon-rank',
            index: 'message',
            title: '短信统计'
        }
    ]
}