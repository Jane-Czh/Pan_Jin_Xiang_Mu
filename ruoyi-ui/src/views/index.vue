<template>
  <div class="home">
    <div class="overlay">
      <el-row :gutter="20">
        <el-col :sm="24" :lg="12" style="padding-left: 20px">
          <h2>盘锦合力管理系统</h2>
        </el-col>
      </el-row>
    </div>
    <img src="./image.png" alt="Image Description" class="full-screen-image">
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      version: "3.8.7"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  position: relative;
  width: 100%;
  height: 100vh; /* 使容器的高度为视口高度 */
  overflow: hidden;
}

.full-screen-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 使图片覆盖容器，并保持比例 */
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px;
  background: rgba(255, 255, 255, 0.8); /* 半透明的背景 */
  z-index: 1; /* 确保在图片上方 */
}

h2 {
  margin-top: 10px;
  font-size: 26px;
  font-weight: 100;
}

/* 你原来的样式可以继续放在这里 */
blockquote {
  padding: 10px 20px;
  margin: 0 0 20px;
  font-size: 17.5px;
  border-left: 5px solid #eee;
}

hr {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 0;
  border-top: 1px solid #eee;
}

.col-item {
  margin-bottom: 20px;
}

ul {
  padding: 0;
  margin: 0;
  list-style-type: none;
}



h4 {
  margin-top: 0px;
}

p {
  margin-top: 10px;

  b {
    font-weight: 700;
  }
}

.update-log {
  ol {
    display: block;
    list-style-type: decimal;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0;
    margin-inline-end: 0;
    padding-inline-start: 40px;
  }
}
</style>
