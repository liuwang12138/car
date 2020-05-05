<template>
  <div class="menu">
    <div class="left">沃尔沃</div>
    <div class="midmenu">
      <el-menu
        :default-active="onRoutes"
        class="el-menu"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router
      >
        <template v-for="item in menuData">
          <template v-if="item.submenu">
            <el-submenu :index="item.index" :key="item.index">
              <template slot="title">
                <span slot="title">{{ item.title }}</span>
              </template>
              <template v-for="subItem in item.submenu">
                <el-menu-item :index="subItem.index" :key="subItem.index">
                  {{ subItem.title }}
                </el-menu-item>
              </template>
            </el-submenu>
          </template>
          <template v-else>
            <el-menu-item :index="item.index" :key="item.index">
              <span slot="title">{{ item.title }}</span>
            </el-menu-item>
          </template>
        </template>
      </el-menu>
    </div>
    <div class="header-right">
      <div class="header-user-con">
        <el-dropdown class="user-name" trigger="click" @command="handleCommand">
          <span class="el-dropdown-link">{{ username }}</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Menu",
  data() {
    return {
      menuData: [
        {
          index: "Home",
          title: "首页"
        },
        {
          index: "List",
          title: "车型列表"
        },
        {
          index: "Maps",
          title: "地图"
        },
        {
          index: "Future",
          title: "未来驾驶"
        },
        {
          index: "Mine",
          title: "我的信息"
        }
      ]
    };
  },
  computed: {
    username() {
        let username = localStorage.getItem('ms_username');
        return username ? username : '未登录';
    },
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  methods: {
    handleCommand(command) {
      if (command == "loginout") {
        localStorage.removeItem('ms_username');
        this.$router.push("/Login");
      }
    }
  }
};
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
.menu
  position: relative;
  width: 100%;
  height: 60px;
  background: #545c64
  .left
    position: absolute;
    padding: 15px 21px;
    cursor: pointer;
    font-size: 20px;
    font-weight: normal;
    color: #fff;
  .midmenu
    position: absolute;
    left: 100px;
  .header-right
    position: absolute
    top: 18px
    right: 10px
    .el-dropdown-link 
      color: #fff
</style>
