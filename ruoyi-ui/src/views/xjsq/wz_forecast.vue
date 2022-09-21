<template>
  <div class="echarts fade-in-left" v-loading="loading">
    <div class="data">
      <span>数据时间</span>：<br>
      <el-select style="width: 100px" v-model="value" @change="list" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <br>
      <span>站点</span>：<br>
      <el-select style="width: 100px" v-model="zd" placeholder="请选择">
        <el-option
          v-for="item in zdoptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div id="echart">
    </div>
  </div>
</template>

<script>
  import Echarts from 'echarts'
  import axios from 'axios'
  import {forecast} from '@/api/system/d.js'

  export default {
    name: "wz_forecast",
    data() {
      return {
        Chart: {},
        wz: {
          time: [],
          z: [],
          forecast6h: [],
          forecast12h: [],

        },
        value: "48小时",
        zd: "梧州",
        fontSize: 23,
        loading:false,
        options: [
          {
            value: 24,
            label: '24小时'
          },
          {
            value: 48,
            label: '48小时'
          }, {
            value: 72,
            label: '72小时'
          }, {
            value: 96,
            label: '96小时'
          }],
        zdoptions: [{
          value: "10000003",
          label: '梧州'
        }]
      }
    },
    mounted() {
      let _this = this;
      var fn=function() {
        _this.list();
        return fn
      };
      const timer=setInterval(fn(),600000);
      this.$once('hook:beforeDestroy', () => {
        clearInterval(timer);
      });
      this.landscape();
      window.onresize = function () {
        _this.landscape();
        _this.Chart.resize();
      };
    },
    methods: {
      list(value) {
        this.loading = true;
        this.wz= {
          time: [],
            z: [],
            forecast6h: [],
            forecast12h: [],

        };
        let time = 48;
        if (value){
          time = value
        }
        forecast({stid: 10000003, hour: time}).then(axios.spread((res1, res2, res3) => {
          if (res1.data.code !== 200 || res2.data.code !== 200 || res3.data.code !== 200) {
            return this.$modal.msgError("数据异常，请刷新重试！");
          }
          var data1 = res1.data.rows;
          var data2 = res2.data.rows;
          var data3 = res3.data.rows;
          if (data3) {
            this.wz.time = Array.from((data3), ({datatm}) => datatm);
            this.wz.forecast12h = Array.from((data3), ({z}) => z);
            this.wz.time.forEach(item => {
              this.wz.z.push(null);
              this.wz.forecast6h.push(null)
            });
          }
          if (data1) {
            data1.forEach((item) => {
              if (this.wz.time.indexOf(item.datatm) !== -1) {
                this.wz.z[this.wz.time.indexOf(item.datatm)] = item.z;
              }
            });
          }
          if (data2) {
            data2.forEach((item) => {
              if (this.wz.time.indexOf(item.datatm) !== -1) {
                this.wz.forecast6h[this.wz.time.indexOf(item.datatm)] = item.z;
              }
            });
          }
          this.Echart();
        }))
      },
      //使用移动端时横屏显示
      landscape() {
        const width = document.documentElement.clientWidth;
        const height = document.documentElement.clientHeight;

        if (width < 767) {
          const contentDOM = document.getElementById('echart');
          contentDOM.style.width = "Calc(100vh - 90px)";
          contentDOM.style.height = width + 'px';
          contentDOM.style.top = (height - width - 90) / 2 + 'px';
          contentDOM.style.left = 0 - (height - width - 90) / 2 + 'px';
          contentDOM.style.transform = 'rotate(90deg)';
        } else {
          const contentDOM = document.getElementById('echart');
          contentDOM.style.height = "Calc(100vh - 90px)";
          contentDOM.style.width = "100vw";
          contentDOM.style.top = '0px';
          contentDOM.style.left = '0px';
          contentDOM.style.transform = 'none';
        }
        if (width > 400) {
          this.fontSize = 23
        } else {
          this.fontSize = 14
        }
      },
      Echart() {
        this.Chart = Echarts.init(document.getElementById("echart"));
        this.Chart.setOption({
          grid: {
            top: 70,
            bottom: 50
          },
          title: {
            text: "水情预测趋势曲线",
            textStyle: {
              fontStyle: 'italic',
              fontSize: this.fontSize
            }
          },
          tooltip: {
            trigger: "axis",
          },

          legend: {
            data: ["梧州水位实际值", "梧州水位6小时预测值", "梧州水位12小时预测值"],
            top: "2%",
            right: " 2%"
          },
          xAxis: {
            type: 'category',
            data: this.wz.time,
          },
          yAxis: {
            name: "m",
            type: 'value',
            min: function (value) {
              return parseInt(value.min * 0.8)
            }
            ,
            max: function (value) {
              return parseInt((value.max) * (1.2))
            },
          },
          series: [
            {
              name: "梧州水位实际值",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.wz.z,
              type: 'line',
              smooth: true,
              itemStyle: {
                normal: {
                  color: "#1c84c6",//折线点的颜色
                  lineStyle: {
                    color: "#1c84c6"//折线的颜色#1ab394
                  }
                }
              }
            },
            {
              name: "梧州水位6小时预测值",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.wz.forecast6h,
              type: 'line',
              smooth: true,
              itemStyle: {
                normal: {
                  color: "#1ab394",//折线点的颜色
                  lineStyle: {
                    color: "#1ab394"//折线的颜色
                  }
                }
              }
            },
            {
              name: "梧州水位12小时预测值",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.wz.forecast12h,
              type: 'line',
              smooth: true,
              itemStyle: {
                normal: {
                  color: "#b3b141",//折线点的颜色
                  lineStyle: {
                    color: "#b3b141"//折线的颜色
                  }
                }
              }
            }
          ]
        });
        this.loading=false;
      },

    }

  }
</script>

<style scoped>
  @media (max-width: 767px) {
    .data {
      display: none;
    }
  }

  /*}*/
  .echarts {
    position: relative;

  }

  .data {
    position: absolute;
    top: Calc(4% + 32px);
    left: 5px;
    z-index: 999;
  }

  .data > span {
    display: inline-block;
    width: 60px;
    text-align: justify;
    text-justify: distribute-all-lines;
    text-align-last: justify;
  }

  .data > span:nth-of-type(2) {
    margin-top: 20px;
  }
</style>
