<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
html,
body,
#app,
.wrapper {
    width: 100%;
    height: 100%;
    overflow: hidden;
}
.home, .list, .mine, .maps, .detail, .future {
    width: 100%;
    height: 100%;
    overflow-y: scroll;
}
*{
  margin: 0;
  padding: 0;
}
.h2 {
    font-size: 26px;
    line-height: 32px;
    margin: 40px;
    color: #161618;
    font-weight: normal;
}
</style>
