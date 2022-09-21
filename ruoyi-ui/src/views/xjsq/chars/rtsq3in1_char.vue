<template>

  <div class="echarts" v-loading="show">
    <div>
      <ul>
        <li>坝上水位:{{info.bssw}}m</li>
        <li>坝下水位:{{info.bxsw}}m</li>
        <li>出库流量:{{info.ckll}}m³/s</li>
        <li>入库流量:{{info.bssw}}m³/s</li>
        <li>功率:{{info.qcyg}}MW</li>
      </ul>

      <!--<span>数据更新时间：</span>-->
    </div>
    <div>
    <span>数据时间：</span>
    <el-select style="width: 30%" v-model="value" @change="chart" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    </div>
    <span class="sw">水位数据更新时间：{{list.swgxtime}}</span>
    <span class="ll">流量数据更新时间：{{list.llgxtime}}</span>
    <span class="gl">功率数据更新时间：{{list.glgxtime}}</span>
    <div id="swechart">
    </div>
    <div id="llechart">
    </div>
    <div id="glechart">
    </div>
  </div>
</template>

<script>
  import Echarts from 'echarts'
  import {listRtsq3in1} from "@/api/system/rtsq3in1.js"

  export default {
    name: "rtsq3in1_char",
    data() {
      return {
        swChart: {},
        llChart: {},
        glChart: {},
        zd: "",
        options: [{
          value: 24,
          label: '24小时'
        }, {
          value: 48,
          label: '48小时'
        }, {
          value: 72,
          label: '72小时'
        }, {
          value: 96,
          label: '96小时'
        }],
        value: '48小时',
        info: this.data,
        list: {
          swgxtime: "",
          llgxtime: "",
          glgxtime: "",
          swtime: [],
          lltime: [],
          gltime: [],
          bssw: [],
          bxsw: [],
          ckll: [],
          rkll: [],
          gl: []
        },
        show: true,
        itemStyle: {
          normal: {
            color: "#1c84c6",//折线点的颜色
            lineStyle: {
              color: "#1c84c6"//折线的颜色
            }
          }
        },
        itemStylea: {
          normal: {
            color: "#1ab394",//折线点的颜色
            lineStyle: {
              color: "#1ab394"//折线的颜色
            }
          }
        }
      }
    },
    props: {
      data: {
        type: Object,
        defalut: {}
      }
    },
    methods: {
      swEchart() {
        this.swChart = Echarts.init(document.getElementById("swechart"));
        this.swChart.setOption({
          title: {
            text: "水位",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["坝上水位", "坝下水位"],
          },
          xAxis: {
            type: 'category',
            data: this.list.swtime
          },

          yAxis: {
            name: "m",
            type: 'value'
          },
          series: [
            {
              name: "坝上水位",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.bssw,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStyle
            },
            {
              name: "坝下水位",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.bxsw,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStylea
            }
          ]
        })
      },
      llEchart() {
        this.llChart = Echarts.init(document.getElementById("llechart"));
        this.llChart.setOption({
          title: {
            text: "流量",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["出库流量", "入库流量"],
          },
          xAxis: {
            type: 'category',
            data: this.list.lltime
          },

          yAxis: {
            name: "m³/s",
            type: 'value'
          },
          series: [
            {
              name: "出库流量",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.ckll,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStyle
            },
            {
              name: "入库流量",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.rkll,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStylea
            }
          ]
        })
      },
      glEchart() {
        this.glChart = Echarts.init(document.getElementById("glechart"));
        this.glChart.setOption({
          title: {
            text: "功率",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["功率"],
          },
          xAxis: {
            type: 'category',
            data: this.list.gltime
          },

          yAxis: {
            name: "MW",
            type: 'value'
          },
          series: [
            {
              name: "功率",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.gl,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStyle
            }
          ]
        })
      },
      chart(value) {
        this.show = true;
        let hour = 48;
        if (value) {
          hour = value
        }
        listRtsq3in1({csid: this.data.csid, hour: hour}).then((res) => {
          this.list = {
            swtime: [],
            lltime: [],
            gltime: [],
            bssw: [],
            bxsw: [],
            ckll: [],
            rkll: [],
            gl: []
          };
          var bssw = res["坝上水位"];
          var bxsw = res["坝下水位"];
          var ckll = res["出库流量"];
          var rkll = res["入库流量"];
          var gl = res["全厂有功"];

          if (bssw) {
            var bsswtime = Array.from((bssw), ({time}) => time);
            this.list.swtime = this.list.swtime.concat(bsswtime);
          }

          if (bxsw) {
            var bxswtime = Array.from((bxsw), ({time}) => time);
            this.list.swtime = this.list.swtime.concat(bxswtime);
          }

          if (bssw || bxsw) {
            this.list.swtime.sort((a, b) => new Date(a) - new Date(b));//排序
            this.list.swtime = new Set(this.list.swtime);//去重
            this.list.swtime = Array.from(this.list.swtime); //重新转化为数组
            this.list.swgxtime = this.list.swtime.slice(-1).toString();//直接取最后一个时间作为更新时间
            this.list.swtime.forEach(() => {
              this.list.bssw.push(null);
              this.list.bxsw.push(null);
            });

            if (bssw) {
              bssw.forEach((item) => {
                if (this.list.swtime.indexOf(item.time) !== -1) {
                  this.list.bssw[this.list.swtime.indexOf(item.time)] = item.value;
                }
              });
            }

            if (bxsw) {
              bxsw.forEach((item) => {
                if (this.list.swtime.indexOf(item.time) !== -1) {
                  this.list.bxsw[this.list.swtime.indexOf(item.time)] = item.value;
                }
              });
            }
          }

          if (ckll) {
            var cklltime = Array.from((ckll), ({time}) => time);
            this.list.lltime = this.list.lltime.concat(cklltime);
          }

          if (rkll) {
            var rklltime = Array.from((rkll), ({time}) => time);
            this.list.lltime = this.list.lltime.concat(rklltime);
          }

          if (ckll || rkll) {
            this.list.lltime.sort((a, b) => new Date(a) - new Date(b));//排序
            this.list.lltime = new Set(this.list.lltime);//去重
            this.list.lltime = Array.from(this.list.lltime); //重新转化为数组
            this.list.llgxtime = this.list.lltime.slice(-1).toString();//直接取最后一个时间作为更新时间

            this.list.lltime.forEach(() => {
              this.list.ckll.push(null);
              this.list.rkll.push(null);
            });

            if (ckll) {
              ckll.forEach((item) => {
                if (this.list.lltime.indexOf(item.time) !== -1) {
                  this.list.ckll[this.list.lltime.indexOf(item.time)] = item.value;
                }
              });
            }
            if (rkll) {
              rkll.forEach((item) => {
                if (this.list.lltime.indexOf(item.time) !== -1) {
                  this.list.rkll[this.list.lltime.indexOf(item.time)] = item.value;
                }
              });
            }
          }


          if (gl) {
            this.list.gltime = Array.from((gl), ({time}) => time);
            this.list.gl = Array.from((gl), ({value}) => value);
            this.list.glgxtime = this.list.gltime.slice(-1).toString();//直接取最后一个时间作为更新时间
          }


          this.swEchart();
          this.llEchart();
          this.glEchart();
          this.show = false;
        })
      }

    },
    mounted() {
      this.chart();
      let _this = this;
      window.onresize = function () {
        _this.swChart.resize();
        _this.llChart.resize();
        _this.glChart.resize()

      };
    }
  }
</script>

<style scoped>
  ul,li {
    margin: 0;
    padding: 0;
  }

  .echarts {
    position: relative;
  }

  /*每个折线图*/
  #swechart,#llechart,#glechart {
    height: 250px;
    position: relative;
  }
  #swechart{
   margin-top: 15px;
  }


  .echarts > div > ul > li {
    /*background-color: pink;*/
    width: 20%;
    float: left;
    /*padding-left: 10px;*/
    margin-bottom: 10px;
    list-style: none;
    font-size: 14px;
    white-space: nowrap;


  }



  .echarts > span {
    font-size: 16px;
    white-space: nowrap;

  }



  .sw, .ll, .gl {
    position: absolute;
  }

  .sw {
    top: 105px;
    right: 20%;
  }

  .ll {
    top: 355px;
    right: 20%;

  }

  .gl {
    top: 605px;
    right: 20%;

  }

  @media (max-width: 767px) {
    .echarts>div>ul>li{
      width: 33%;

    }
    .echarts>div>ul>li:nth-child(5){
      margin: 0 30% 0 3%;

    }
    .echarts > div:nth-child(1){
      height: 65px;

    }
    .echarts > span {
      font-size: 14px;
      white-space: nowrap;

    }
    .sw {
      top: 135px;
      right: 20%;
    }

    .ll {
      top: 385px;
      right: 20%;

    }

    .gl {
      top: 635px;
      right: 20%;

    }

  }
</style>
<style>
  @media (max-width: 767px) {
    .el-dialog__body{
      padding: 10px 15px 15px 15px;
    }

  }

  /* 弹出栏向上偏移 */
  .el-popper[x-placement^=bottom] {
    /*margin-top: 12px !important;*/
    border-radius: 0;
  }

  .el-scrollbar__view > li {
    text-align: center;
  }
</style>
