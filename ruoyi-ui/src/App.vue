<template>
  <div id="app">
    <router-view v-if="isShow"></router-view>
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  // 刷新页面相关
  name: "App",
  //页面刷新
  provide() {
    return {
      reload: this.reload,
    };
  },
  data() {
    return {
      //页面刷新
      isShow: true,
    };
  },
  components: { ThemePicker },
  methods: {
    //页面刷新
    reload() {
      this.isShow = false;
      this.$nextTick(function () {
        this.isShow = true;
      });
    },
  },
  metaInfo() {
    return {
      title:
        this.$store.state.settings.dynamicTitle &&
        this.$store.state.settings.title,
      titleTemplate: (title) => {
        return title
          ? `${title} - ${process.env.VUE_APP_TITLE}`
          : process.env.VUE_APP_TITLE;
      },
    };
  },
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
