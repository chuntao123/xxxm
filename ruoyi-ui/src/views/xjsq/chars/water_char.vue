<template>
  <div class="echarts" v-loading="show">
    <!--<h4>{{zd}}船闸水情数据曲线图</h4>-->
    <!--<span class="glyphicon glyphicon-remove"></span>-->
    <div>
      <ul>
        <li>水位:{{info.waterZ}}m</li>
        <li>流量:{{info.waterQ}}m³/s</li>
        <li>水势:{{info.waterWptn}}</li>
        <li>时效性:{{info.waterTmdelay}}</li>
      </ul>
      <span>数据时间：</span>
      <el-select style="width: 30%" v-model="value"  @change="chart" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <!--<span>数据更新时间：</span>-->
    </div>
    <span class="sw">水位数据更新时间：{{list.gxtime}}</span>
    <span class="ll">流量数据更新时间：{{list.gxtime}}</span>
    <div id="swechart">
    </div>
    <div id="llechart">
    </div>
  </div>
</template>

<script>
  import Echarts from 'echarts'
  import {listD,listrain} from  "@/api/system/d.js"
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
        info:this.data,
        list: {
          gxtime:"",//更新时间
          time:[],
          sw: [],
          ll: [],
        },
        show:true,
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
    props:{
      data:{
        type:Object,
        defalut:{}
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
            data: ["水位"],
          },
          xAxis: {
            type: 'category',
            data: this.list.time
          },

          yAxis: {
            name: "m",
            type: 'value'
          },
          series: [
            {
              name: "水位",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.sw,
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
            data: ["流量"],
          },
          xAxis: {
            type: 'category',
            data: this.list.time
          },

          yAxis: {
            name: "m³/s",
            type: 'value'
          },
          series: [
            {
              name: "流量",
              symbol: 'circle',//折线点设置为实心点
              symbolSize: 1,   //折线点的大小
              connectNulls: true,
              data: this.list.ll,
              type: 'line',
              smooth: true,
              itemStyle: this.itemStylea
            }
          ]
        })
      },
      chart(value){
        this.show=true;
        let hour = 48;
        if (value) {
          hour = value
        }
        listD({stid:this.data.stid,hour:hour}).then((res)=>{
          this.list={
            time: [],
              sw: [],
              ll: [],
          };
          var data=res.rows;
          if (data.length!==0){
            this.list.time=Array.from((data), ({datatm}) => datatm);
            this.list.gxtime=this.list.time.slice(-1).toString();//直接取最后一个时间作为更新时间
            this.list.sw=Array.from((data), ({z}) => z);
            this.list.ll=Array.from((data), ({q}) => q);

          }
          this.swEchart();
          this.llEchart();
          this.show=false;
        })
      }

    },
    mounted() {
      listrain({stid:10000058,hour:96}).then(res=>{
        console.log(res)
      });
      this.chart();
      let _this = this;
      window.onresize = function () {
        _this.swChart.resize();
        _this.llChart.resize();
      };
    }
  }
</script>

<style scoped>
  * {
    margin: 0;
    padding: 0;
  }

  .echarts {
    position: relative;
  }

  /*每个折线图*/
  .echarts div {
    height: 250px;
  }

  .echarts div:nth-of-type(1) {
    height: 80px;
  }

  .echarts > div > ul > li {
    /*background-color: pink;*/
    width: 25%;
    float: left;
    /*padding-left: 10px;*/
    margin-bottom: 10px;
    list-style: none;
    font-size: 14px;
    white-space:nowrap;


  }

  .echarts > div:nth-of-type(1) span {
    font-size: 16px;
    white-space:nowrap;
  }
  .echarts > div:nth-of-type(1) span:nth-of-type(2) {
    margin-left: 20px;
  }

  .sw,.ll,.gl{
    position: absolute;
  }
  .sw{
    top:106px;
    right: 20%;
  }
  .ll{
    top: 356px;
    right: 20%;

  }
  .gl{
    top: 606px;
    right: 20%;

  }


</style>
<style>
  /* 弹出栏向上偏移 */
  .el-popper[x-placement^=bottom] {
    /*margin-top: 12px !important;*/
    border-radius: 0;
  }

  .el-scrollbar__view > li {
    text-align: center;
  }
</style>
