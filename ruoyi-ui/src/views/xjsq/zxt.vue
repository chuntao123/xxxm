<template>
  <div class="echarts" v-loading="show">
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
  import {listRtsq3in1,menulistRtsq3in1} from "@/api/system/rtsq3in1.js"

  export default {
    name: "zxt",
    data() {
      return {
        swChart: {},
        llChart: {},
        glChart: {},
        zd: [],
        list: {
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
    methods: {
      swEchart(csname) {
        this.swChart = Echarts.init(document.getElementById("swechart"));
        this.swChart.setOption({
          title: {
            text: csname+"水位",
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
      llEchart(csname) {
        this.llChart = Echarts.init(document.getElementById("llechart"));
        this.llChart.setOption({
          title: {
            text: csname+"流量",
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
      glEchart(csname) {
        this.glChart = Echarts.init(document.getElementById("glechart"));
        this.glChart.setOption({
          title: {
            text: csname+"功率",
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
      csid(){
        menulistRtsq3in1().then(resp =>{
          this.show = true;
          var data=resp.rows;
          let csid= Array.from((data), ({csid}) => csid);
          let csname = Array.from((data), ({csname}) => csname);
          csid = new Set(csid);//去重
          csid = Array.from(csid); //重新转化为数组
          csname = new Set(csname);
          csname = Array.from(csname);
          let i = 0;
          let that=this;
          var fn=function() {
            if (i === csid.length - 1){
              i = 0;
            }
            that.chart(csid[i],csname[i]);
            i++;
            return fn
          };
          const timer=setInterval(fn(),60000);
          this.$once('hook:beforeDestroy', () => {
            clearInterval(timer);
          })
        });
      },
      chart(csid,csname) {
        let hour = 48;
        listRtsq3in1({csid: csid, hour: hour}).then((res) => {
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


          this.swEchart(csname);
          this.llEchart(csname);
          this.glEchart(csname);
          this.show = false;
        })
      }

    },
    mounted() {
      this.csid();
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
  .echarts {
    height: 100vh;
    position: relative;
  }

  /*每个折线图*/
  #swechart,#llechart,#glechart {
    height: 33.3%;
  }






  @media (max-width: 767px) {


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
