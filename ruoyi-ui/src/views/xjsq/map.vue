<template>
  <div id="test_div" v-loading="loading">
    <span class="el-icon-paperclip righticon" @click="drawershow = true"></span>
    <el-drawer
      :visible.sync="drawershow"
      direction="rtl"
      size="100%"
      :with-header="false"
      style="top: 52px"
      :modal="false"
    >
      <span class="el-icon-close close" @click="drawershow = false"></span>
      <div class="sqtitle" v-if="!loading">
        <el-tabs v-model="activeName">
          <el-tab-pane label="船闸水情" name="czsq">
            <czsqtable :data="czsqdata"></czsqtable>
          </el-tab-pane>
          <el-tab-pane label="水文水情" name="swsq">
            <swsqtable :data="swsqdata"></swsqtable>
          </el-tab-pane>
          <el-tab-pane label="雨量水情" name="ylsq">
            <ylyqtable :data="ylyqdata"></ylyqtable>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-drawer>

    <el-dialog :title="title+'船闸水情数据曲线图'" :visible.sync="rtsqshow" :modal="false">
      <rtsqchar :data="data" v-if="rtsqshow"></rtsqchar>
    </el-dialog>
    <el-dialog :title="title+'水文水情数据曲线图'" :visible.sync="watershow" :modal="false">
      <waterchar :data="data" v-if="watershow"></waterchar>
    </el-dialog>

    <div class="amap-page-container map">
      <div id="amap-demo" class="amap-demo">
      </div>
    </div>
  </div>
</template>

<script>
  import {lazyAMapApiLoaderInstance} from 'vue-amap';
  import {getmap} from "@/api/system/all.js"
  import {listSwz, threelist} from "@/api/system/swz.js"

  const loadPromise = lazyAMapApiLoaderInstance.load();
  export default {
    data() {
      return {
        map: null,
        // 是否显示折线图
        rtsqshow: false,
        watershow: false,
        title: "",//折线图站点标题
        data: [],
        // 控制侧边弹出
        drawershow: false,
        // 是否固定弹窗
        switchshow: false,
        //显示的组件
        activeName: "czsq",
        czsqdata: [],
        swsqdata: [],
        ylyqdata: [],
        loading:false
      }
    },
    components: {
      rtsqchar: () => import('@/views/xjsq/chars/rtsq3in1_char.vue'),
      waterchar: () => import('@/views/xjsq/chars/water_char.vue'),
      czsqtable: () => import('@/views/xjsq/table/czsqtable.vue'),
      swsqtable: () => import('@/views/xjsq/table/swsqtable.vue'),
      ylyqtable: () => import('@/views/xjsq/table/ylyqtable.vue'),
    },
    methods: {
      initMap() {
        this.loading=true;
        loadPromise.then(() => {
          this.map = new AMap.Map('amap-demo', {
            center: [108.924, 23.217771],
            zoom: 8,
            resizeEnable: true,
            rotateEnable: true,
            pitchEnable: true,
            pitch: 45,
            rotation: 0,
            viewMode: '3D',//开启3D视图,默认为关闭
            buildingAnimation: true,//楼块出现是否带动画
            expandZoomRange: true,
            zooms: [3, 20],

          });
        });
        this.drawDistrictBorder()
      },
      // 绘制区域边线
      drawDistrictBorder() {

        let opts = {
          subdistrict: 1,
          // 是否返回行政区边界坐标点
          extensions: "all",
          // 设置查询行政区级别为省
          level: "Province",
        };
        let that = this;
        // 创建DistrictSearch对象
        const district = new AMap.DistrictSearch(opts);
        district.search("广西壮族自治区", function (status, result) {
          let bounds = result.districtList[0].boundaries;
          for (let i = 0; i < bounds.length; i += 1) {
            // 绘制边界线
            new AMap.Polyline({
              path: bounds[i],
              strokeColor: "red",
              strokeWeight: 4,
              map: that.map,
            });
          }
        });

        this.onemarker();
        this.twomarker();
        this.threemarker();
        this.loading = false;
      },
      // 添加一类标记点
      onemarker() {
        getmap().then(res => {
          this.czsqdata = res.data;
          loadPromise.then(() => {
            let markers = [];
            res.data.forEach(item => {
              var markerContent = '' +
                '<div class="marker">' +
                '<i class="iconfont">&#xe709;</i><br>' +
                '<p>' + item.csname + '</p>' +
                '<p>坝上水位:' + item.bssw + 'm,坝下水位:' + item.bxsw + 'm</p>' +
                '<p>出库流量:' + item.ckll + 'm³/s,入库流量:' + item.rkll + 'm³/s</p>' +
                '<p>功率:' + item.qcyg + 'MV</p>' +
                '</div>';
              let marker = new AMap.Marker({
                position: [item.wzCsList[0].ceslo, item.wzCsList[0].cntla],
                content: markerContent,
              });
              marker.on('click', () => {
                this.rtsqcharts(item)
              });
              markers.push(marker)
            });

            this.map.add(markers);
          });
        })

      },
      // 添加二类标记点

      twomarker() {
        listSwz().then(res => {
          this.swsqdata = res.rows;
          loadPromise.then(() => {
            let markers = [];
            res.rows.forEach(item => {
              var markerContent = '' +
                '<div class="marker" style="color: #1ab394">' +
                '<i class="iconfont" style="color: #1ab394">&#xe709;</i><br>' +
                '<p>' + item.stnm + '</p>' +
                '<p>水位:' + item.waterZ + 'm</p>' +
                '<p>流量:' + item.waterQ + 'm³/s</p>' +
                '</div>';
              let marker = new AMap.Marker({
                position: [item.lgtd, item.lttd],
                content: markerContent,
              });
              marker.on('click', () => {
                this.watercharts(item)

              });
              markers.push(marker)
            });

            this.map.add(markers);
          });

        })

      },
      // 三类标记
      threemarker() {
        threelist().then(res => {
          this.ylyqdata = res.rows;
          loadPromise.then(() => {
            let markers = [];
            res.rows.forEach(item => {
              var markerContent = '' +
                '<div class="marker" style="color:#5f81b3">' +
                '<i class="iconfont" style="color: #5f81b3">&#xe709;</i><br>' +
                '<p>' + item.stnm + '</p>' +
                '<p>水位:' + item.waterZ + 'm</p>' +
                '<p>流量:' + item.waterQ + 'm³/s</p>' +
                '</div>';
              let marker = new AMap.Marker({
                position: [item.lgtd, item.lttd],
                content: markerContent,
              });
              marker.on('click', () => {
                this.watercharts(item)
              });
              markers.push(marker)
            });

            this.map.add(markers);
          });

        })
      },
      rtsqcharts(data) {
        this.title = data.csname;
        this.data = data;
        this.rtsqshow = true;
      },
      watercharts(data) {
        this.title = data.stnm;
        this.data = data;
        this.watershow = true;
      },
      // 监控鼠标
      jk() {
        var that = this;

        function mousePos(e) {

          e = e || window.event;
          var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;//分别兼容ie和chrome
          var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
          var x = e.pageX || (e.clientX + scrollX);//兼容火狐和其他浏览器
          var y = e.pageY || (e.clientY + scrollY);
          var w = window.innerWidth
            || document.documentElement.clientWidth
            || document.body.clientWidth;
          if ((w - x) < w * 0.1 && w > 766) {
            that.drawershow = true;
          }
        }
        var test = document.querySelector("#test_div");
        test.onmousemove = function (e) {
          mousePos(e);
        }
      }


    },
    mounted() {

      let that=this;
      var fn=function() {
        that.initMap();
        return fn
      };
      const timer=setInterval(fn(),600000);
      this.$once('hook:beforeDestroy', () => {
        clearInterval(timer);
      });
      this.jk();
    },
  }
</script>

<style scoped>
  .amap-demo {
    min-height: Calc(100vh - 52px);
  }

  #test_div {
    position: relative;
  }
  .close{
    position:absolute;
    left: 0;
    margin-left: 5%;
    margin-top: 2%;
    cursor: pointer;
    border: 2px solid #cadfff;
  }
  .righticon {
    position: absolute;
    top: 130px;
    right: 0;
    z-index: 999;
    font-size: 30px;
    background-color: #9ac4ff;
    display: none;
  }

  .sqtitle {
    padding-left: 5%;
    margin-top: Calc(3% + 20px);

  }

  @media (max-width: 766px) {
    .righticon {
      display: block;
    }
  }
</style>
<style>
  @media (max-width: 1200px) {
    .el-drawerwith {
      width: 100%;
    }

    .el-dialog {
      width: 100%
    }
  }

  .marker {
    color: #1c84c6;
  }

  .marker > p, .marker > em {
    white-space: nowrap;
    font-size: 12px;
    border: 1px solid #91d5ff;
    border-radius: 4px;
    padding: 0 7px;
    margin: 5px 0;
    background-color: #ffffff;
  }

  .marker > p:first-of-type {
    display: inline;
  }

  .iconfont {
    color: #1c84c6;
  }

  .iconfont:hover {
    color: #1c84c6 !important;
  }
</style>
