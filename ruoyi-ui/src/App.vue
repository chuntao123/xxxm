<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default  {
  name:  'App',
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    }
}
</script>
<style>
  .amap-logo{
    display: none;
    opacity:0 !important;
  }
  .amap-copyright {
    opacity:0;
  }
  body{
    box-sizing:border-box
  }
</style>
