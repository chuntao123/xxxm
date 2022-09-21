<template>
  <div>
    <div class="d-bg">
      <div class="main-box">
          <div span="24">
            <div style="color: white;text-align: center;padding-top: 8px;">
              <div :style="tbStyle">
                <table id="water-info" class="main_table">
                  <thead>
                  <tr>
                    <th>河流</th>
                    <th>站名</th>
                    <th>时间</th>
                    <th>水位</th>
                    <th>流量</th>
                    <th>距警戒</th>
                    <th>站址</th>
                  </tr>
                  </thead>
                  <tbody>
                  <template v-for="(item,idx) in list">
                    <tr v-if="idx<20" :class="{'odd':idx%2!=0,'warnning':item.outOfLimit}">
                      <td>{{item.rvnm}}</td>
                      <td>{{item.stnm}}</td>
                      <td>{{item.waterDatatm}}</td>
                      <td>{{item.waterZ}}</td>
                      <td>{{item.waterQ}}</td>
                      <td>{{item.warn}}</td>
                      <td>{{item.stadrShort}}</td>
                    </tr>
                  </template>

                  <tr :style="scrollStyle"></tr>
                  <tr v-for="(item,idx) in scrollData" :key="idx"
                      :class="{'warnning':item.outOfLimit}">
                    <td>{{item.rvnm}}</td>
                    <td>{{item.stnm}}</td>
                    <td>{{item.waterDatatm}}</td>
                    <td>{{item.waterZ}}</td>
                    <td>{{item.waterQ}}</td>
                    <td>{{item.warn}}</td>
                    <td>{{item.stadrShort}}</td>
                  </tr>
                  </tbody>
                </table>
              </div>

            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  import moment from 'moment';

  import request from '@/utils/request'
  export default {
    name: "led",
    data() {
      return {
        city: '梧州',
        visible: false,
        chart: null,
        duty: {},
        list: [],
        scrollData: [],
        queueData: [],
        date: moment().format('YYYY年MM月DD日'),
        day: '',
        modal: {
          visible: false,
          data: [],
          today: {}
        },
        leftBoxStyle: {
          "color": "white", "text-align": "center", "height": "340px"
        },
        tbStyle: {
          "overflow-y": "hidden", "height": "680px"  // 104 + 32 * tableSize
        },
        scrollStyle: {
          "display": "block",
          "transition": "margin-top 0.5s",
          "margin-top": "0"
        },
        interval: null,
        timeout: null,
        timerSchedule: null, // 更新日期
        schedule: null,
        tableSize: 20,
        fixedDataLength: 8,  // 固定行数
      }
    },
    mounted() {
      console.log(this.scrollData);
      this.day = this.getDay();
      // this.getTodayWeather(); // 获取今日值班数据和本市天气
      this.query().then(() => this.scroll());
      this.schedule = setInterval(() => {
        // this.getDuty().then(() => this.getTodayWeather()); // 获取今日值班数据和本市天气
        if (this.interval != null) {
          clearInterval(this.interval);
        }

        this.query().then(() => this.scroll());
      }, 60000);
      this.timerSchedule = setInterval(() => {
        this.date = moment().format('YYYY年MM月DD日');
        this.day = this.getDay();
      }, 1000);
    },
    beforeDestroy() {
      if (this.interval != null) {
        clearInterval(this.interval);
      }
      if (this.timerSchedule != null) {
        clearInterval(this.timerSchedule);
      }
      if (this.schedule != null) {
        clearInterval(this.schedule);
      }
      if (this.timeout != null) {
        clearTimeout(this.timeout);
      }
    },
    methods: {
      scroll() {
        const size = this.tableSize - this.fixedDataLength; // 每页展示多少
        // 一开始就要展示部分数据
        const length = this.queueData.length > size ? size : this.queueData.length;
        let currentIdx = 0;

        while (currentIdx < length) {
          this.scrollData.push(this.queueData[currentIdx]);
          currentIdx++;
        }
        currentIdx--;
        let a = 0;
        this.interval = setInterval(() => {
          if (this.queueData.length > size) {
            if (this.timeout != null) {
              clearTimeout(this.timeout);
            }
            a += 32;
            this.scrollStyle["transition"] = "margin-top 0.5s";
            this.scrollStyle["margin-top"] = (a + "px");
            this.timeout = setTimeout(() => {
              const arr = [];
              if (currentIdx !== this.queueData.length - 1) {
                currentIdx++;
              }
              let tmp = this.queueData[currentIdx];
              for (let i = currentIdx; i > 0; i--) {
                this.queueData[i] = this.queueData[i - 1];
              }
              this.queueData[0] = tmp;
              for (let i = 0; i < size; i++) {
                arr.push(this.queueData[i]);
              }
              this.scrollData = arr;
              this.scrollStyle["transition"] = false;
              this.scrollStyle["margin-top"] = "0px";
              a = 0;
            }, 600);
          }
        }, 3500);
      },
      getDay() {
        const day = moment().day();
        const dayArr = ['日', '一', '二', '三', '四', '五', '六'];
        return '星期' + dayArr[day];
      },
      query() {
        return request.get("/zhexiantu/swj-led/list").then(res => {
          this.list = [];
          this.tableSize = res.data.tableSize;
          this.tbStyle["height"] = this.tableSize * 32 + 40 + "px";
          this.leftBoxStyle["height"] = (this.tableSize * 32 + 60) / 2 - 10 + "px";
          let i = 0;
          while (i < this.fixedDataLength && i < res.data.list.length) {
            this.list.push(res.data.list[i])
            i++;
          }
          let j = 0;
          while (i < res.data.list.length) {
            this.queueData[j] = res.data.list[i];
            i++;
            j++;
          }
        });
      },
    }
  }
</script>

<style scoped>
  .head {
    background: url(./../../assets/images/head_bg.png) no-repeat center center;
    background-size: 100% 100%;
    text-align: center;
    height: 64px;
    position: relative;
    z-index: 100;
  }

  .weather {
    position: absolute;
    left: 15px;
    top: 10px;;
  }

  .weather span {
    color: rgba(255, 255, 255, .7);
    font-size: 18px;
  }

  .main-box {
    padding: 10px 15px 0 15px;
  }

  .boxall {
    border: 0;
    padding: 0 10px 10px 10px;
    background-color: rgba(255, 255, 255, .04);
    background-size: 100% auto;
    position: relative;
    margin-bottom: 20px;
    z-index: 10;
  }

  .boxall:before, .boxfoot:before {
    border-left: 2px solid #02a6b5;
    left: 0;
  }

  .boxall:after, .boxfoot:after {
    border-right: 2px solid #02a6b5;
    right: 0;
  }

  .boxall:before, .boxall:after {
    position: absolute;
    width: .7em;
    height: .7em;
    content: "";
    border-top: 2px solid #02a6b5;
    top: 0;
  }

  .boxfoot {
    position: absolute;
    bottom: 0;
    width: 100%;
    left: 0;
  }

  .boxfoot:before, .boxfoot:after {
    position: absolute;
    width: .7em;
    height: .7em;
    content: "";
    border-bottom: 2px solid #02a6b5;
    bottom: 0;
  }

  .boxall:after, .boxfoot:after {
    border-right: 2px solid #02a6b5;
    right: 0;
  }

  .d-bg {
    min-height: 100vh;
    background: #000d4a url(./../../assets/images/bg.jpg) center top;
    background-size: cover;
  }

  .alltitle {
    line-height: 48px;
    font-size: 24px;
  }


  .duty-text {
    color: white;
    margin-bottom: 10px;
    font-size: 24px;
    font-weight: bold;
    text-align: center;
  }

  .weather-text {
    color: white;
    font-size: 24px;
    font-weight: bold;
    text-align: center;
  }

  .main_table {
    width: 100%;
    margin: 0 auto;

  }
 th{
   text-align: center;
   font-size:20px
 }
  td{
    font-size: 17px !important;
  }
  .main_table .odd {
    background-color: #072951;
    box-shadow: -10px 0px 15px #2C58A6 inset, /*左边阴影*/ 10px 0px 15px #2C58A6 inset;
  }

  .wt-modal .ant-modal-content {
    background-color: transparent;
    box-shadow: 0 0 0;
  }

  #water-info.main_table th {
    height: 40px;
    font-size: 24px;
  }

  #water-info.main_table td {
    font-size: 20px;
    height: 32px;
  }

  .warnning {
    color: red;
  }
</style>
