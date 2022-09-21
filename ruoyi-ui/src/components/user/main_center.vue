<template>
  <div class="fade-in-left">
    <div class="sp">
    <video width="400" controls>
      <source src="./../../assets/video/xj56.mp4" type="video/mp4">
    </video>
    </div>
     <!--当今水情现状面临的问题模块-->
    <div class="water_regimen  slide-in-bottom">
      <h2>西江水情现状面临的问题</h2>
      <div class="waterbd container">
        <!-- 水情左边四个小盒子 -->
        <div class="col-xs-12 col-sm-6 col-md-4">
          <div class="box shadow">
            <h4>数据不实时</h4>
            <p>当今水情数据不能实时同步，难以根据水情及时做出预防措施</p>
          </div>
          <div class="box shadow icon2">
            <h4>获取信息零散、不全面</h4>
            <p>信息获取时不够全面，不能满足行船人的需求</p>
          </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4">
          <div class="box shadow icon3">
            <h4>数据不共享</h4>
            <p>人们对水情和航运的数据缺少，不能合理进行通航调度</p>
          </div>
          <div class="box shadow">
            <h4>数据难分析</h4>
            <p>面对大量复杂的数据难以分析，对预测之后江上状况难以判断</p>
          </div>
        </div>
        <!-- 水情右边大盒子 -->
        <div class="col-xs-12 col-sm-6 col-md-4">
          <div class="shadow wr visible-md visible-lg ">
            <div>
              <h4>数据不能直观显示</h4>
              <p>不能在一定的时间内直观显示数据变化，无法让人一眼明了</p>
            </div>
            <img src="./../../assets/images/index/chuang.png" alt="">
          </div>
        </div>
      </div>
    </div>
    <div class="feature wow slide-in-bottom">
      <h3 class="h2">西江航运水情大数据平台优势</h3>
      <div class="container">
        <div class="col-md-3 col-sm-6 col-xs-12 mb">
          <div class="row">
            <img src="./../../assets/images/index/bg-jianpan.jpg" alt="">
            <i class="iconfont">&#xe6a3;</i>
            <h3>技术</h3>
            <p>丰富的开发实施经验和知识人才储备。<br>
              对多源异构数据有精确的拟合算法。<br>
            </p>
          </div>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12 mb">
          <div class="row">
            <img src="./../../assets/images/index/bg-tese1.jpg" alt="">
            <i class="iconfont">&#xe6de;</i>
            <h3>信息</h3>
            <p>具有一点发布，全网共享的特点。<br>
              借助于地图服务展示水情空间地理位置。
            </p>
          </div>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="row">
            <img src="./../../assets/images/index/bg-chuan.jpg" alt="">
            <i class="iconfont">&#xe6a7;</i>
            <h3>来源</h3>
            <p>专注研究西江流域水情。<br>
              水情数据来源多样,提高预报精度和预见期。<br>
            </p>
          </div>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="row">
            <img src="./../../assets/images/index/0101.jpg" alt="">
            <i class="iconfont">&#xe743;</i>
            <h3>监控</h3>
            <p>水情数据实时监控,提高数据的准确度。<br>
              为通航调度提供解决方案,推进了西江航运事业的发展。</p>
          </div>
        </div>
      </div>
    </div>
    <div class="main">
      <h3 class="h2">相关服务</h3>
      <div class="service container">
        <div class="col-sm-12 col-md-4 col-lg-4 wow slide-in-left">
          <div class="pictureLeft ">
            <img src="./../../assets/images/index/003.jpg" alt="">
          </div>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4 wow slide-in-fwd-center">
          <div class="center row ">
            <div class="pull-left">
              <img src="./../../assets/images/index/bigdata.jpg" alt="">
              <h5>大数据可视化展示</h5>
            </div>
            <div class="pull-left">
              <img src="./../../assets/images/index/761.jpg" alt="">
              <h5>实时水情预测预警</h5>
            </div>
            <div class="pull-left">
              <img src="./../../assets/images/index/ship.jpg" alt="">
              <h5>通航调度分析</h5>
            </div>
            <div class="pull-left">
              <img src="./../../assets/images/index/65749.jpg" alt="">
              <h5>移动端获取数据</h5>
            </div>
          </div>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4 wow slide-in-right mb8">
          <div class="pictureRight ">
            <img src="./../../assets/images/index/004.jpg" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import { WOW } from 'wowjs'
  export default {
    data() {
      return {
        value: "1",
        playshow:"true"
      };
    },

    methods: {},
    watch: {},
    mounted() {
      new WOW({live: false}).init();
    },
  };
</script>


<style scoped>
  @import './../../assets/styles/global.css';
  @import "./../../assets/styles/mainindex.css";
  @import "./../../assets/styles/bootstrap.css";
  video {
    width: 100%;
    height: auto;
    max-height: 60vh;
    object-fit: fill;
  }
  .iconfont {
    color: #ffffff;
  }
  .sp{
    position: relative;
  }
  .sp:hover{
    cursor:pointer;
  }


</style>



