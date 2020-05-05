<template>
    <div class="maps">
        <c-menu></c-menu>
        <div class="amap-wrapper map">
            <el-amap
                ref="map"
                :vid="'amapDemo'"
                :center="center"
                :zoom="zoom"
                :plugin="plugin"
                :events="events"
                class="amap-demo"
            >
                <el-amap-marker v-for="(u, index) in markers" :key="index" :position="u.position"></el-amap-marker>
                <el-amap-marker :position="[121.5273285, 31.21515044]" :icon="icon"></el-amap-marker>
            </el-amap>
        </div>
        <c-footer></c-footer>
    </div>
</template>

<script>
import cMenu from "@/components/Menu"
import cFooter from "@/components/Footer"

export default {
    name: "Maps",
    components: {
        cMenu,
        cFooter
    },
    // data: function() {
    //     return {
    //       zoom: 12,
    //       center: [121.59996, 31.197646],
          
    //     };
    //   },
    data() {
    return {
      center: [121.5273285, 31.21515044],
      zoom: 12,
      position: [121.5273285, 31.21515044],
      icon: '/huoche.png',
      events: {
        init(o){
          console.log(o.getCenter());
        },
        zoomchange: (e) => {
            console.log(e);
        },  
        zoomend: (e) => {
　　　　　　　　//获取当前缩放zoom值
            console.log(this.$refs.map.$$getInstance().getZoom());
            console.log(e);
        },
        click: e => {
          alert('map clicked')
        }
      },
      markers: [
        {
          position: [121.5273285, 31.41515044]
        },
        {
          position: [121.5273286, 31.31515045]
        }
      ],
//使用其他组件
      plugin: [
        {
          pName: 'Scale',
          events: {
            init(instance) {
              console.log(instance)
            }
          }
        },
        {
          pName: 'ToolBar',
          events: {
            init(instance) {
              console.log(instance)
            }
          }
        }
      ]
    }
  },
}
</script>

<style scoped>
.amap-wrapper {
    width: 100%;
    height: 100%;
}
.amap-demo {
    width: 100%;
    height: 100%;
}
</style>