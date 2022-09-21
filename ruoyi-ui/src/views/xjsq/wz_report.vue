<template>
  <div class="container fade-in-left" v-loading="loading">
    <div class="row">
      <div class="col-md-5 top">
        <div class="row">
          <div class="col-md-2">
            <span class="timespan">时间：</span>
          </div>
          <div class="block col-md-10">
            <el-date-picker
              v-model="value"
              type="datetimerange"
              value-format="yyyy-MM-dd HH:mm:ss"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
            >
            </el-date-picker>
          </div>

        </div>
      </div>
      <div class="col-md-7">
        <el-button type="primary" @click="gettotal"><i class="el-icon-search"></i>查询</el-button>
        <el-button type="primary" @click="upload"><i class="el-icon-download"></i>导出</el-button>
        <div class="wcl" v-show="show">
          <span>6H平均误差率：{{avg6h}}%</span>
          <span>12H平均误差率：{{avg12h}}%</span>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <el-table
          :data="Data"
          border
          style="width: 100%">
          <el-table-column
            prop="datatm"
            label="时间"
          >
          </el-table-column>
          <el-table-column
            prop="z"
            label="实际值"
          >
          </el-table-column>
          <el-table-column
            prop="z6h"
            label="6H预测值">
          </el-table-column>
          <el-table-column
            prop="z12h"
            label="12H预测值">
          </el-table-column>
          <el-table-column
            prop="forecast6herror"
            label="6H误差率">
          </el-table-column>
          <el-table-column
            prop="forecast12herror"
            label="12H误差率">
          </el-table-column>
        </el-table>

      </div>
    </div>
    <div class="row">
      <div class="col-md-12 bottom" v-show="show">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[10, 15, 20]"
          :page-size="pagesize"
          background
          layout="sizes, prev, pager, next"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import {alllistD, forecasttotal} from "@/api/system/d.js"

  export default {
    name: "wz_report",
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: '最近一天',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value: [],
        Data: [],
        avg6h: "",
        avg12h: "",
        show: false,
        loading: false,
        currentPage: 1,
        pagesize: 10,
        total: 0,
        starttime: "",
        endtime: ""

      }
    },
    watch: {
      Data() {
        if (this.Data.length !== 0) {
          this.show = true
        } else {
          this.show = false
        }
      }
    },
    methods: {
      gettotal() {
        if (!this.value[0]) {
          return this.$modal.msgError("请选择时间段！");
        }
        this.loading = true;
        this.starttime = this.value[0];
        this.endtime = this.value[1];
        forecasttotal({starttime: this.starttime, endtime: this.endtime}).then(res => {
          this.total = res;
          this.getdata();
        });
      },
      getdata() {
        this.loading = true;
        alllistD({
          starttime: this.starttime,
          endtime: this.endtime,
          currentPage: this.currentPage,
          pagesize: this.pagesize
        }).then(res => {

          var data = res.rows;
          let total6herror = 0;
          let total12herror = 0;
          data.forEach((item, i) => {
            item.forecast6herror = (((Math.abs(item.z6h - item.z) / item.z) * 100).toFixed(3)) + "%";
            item.forecast12herror = (((Math.abs(item.z12h - item.z) / item.z) * 100).toFixed(3)) + "%";
            total6herror = total6herror + parseInt((Math.abs(item.z6h - item.z) / item.z) * 100);
            total12herror = total12herror + parseInt((Math.abs(item.z12h - item.z) / item.z) * 100);
          });
          this.avg6h = (total6herror / data.length).toFixed(3);
          this.avg12h = (total12herror / data.length).toFixed(3);
          this.Data = data;
          this.loading = false;

        })

      },
      //表格数据写入excel
      upload() {
        if (this.Data.length === 0) {
          return this.$modal.msgError("请先查询数据！");
        }
        this.loading = true;
        var that = this;
        alllistD({starttime: this.starttime, endtime: this.endtime, currentPage: 1, pagesize: this.total}).then(res => {

          const data = res.rows;
          let total6herror = 0;
          let total12herror = 0;
          data.forEach((item, i) => {
            item.forecast6herror = (((Math.abs(item.z6h - item.z) / item.z) * 100).toFixed(3)) + "%";
            item.forecast12herror = (((Math.abs(item.z12h - item.z) / item.z) * 100).toFixed(3)) + "%";
            total6herror = total6herror + parseInt((Math.abs(item.z6h - item.z) / item.z) * 100);
            total12herror = total12herror + parseInt((Math.abs(item.z12h - item.z) / item.z) * 100);
          });
          this.avg6h = (total6herror / data.length).toFixed(3);
          this.avg12h = (total12herror / data.length).toFixed(3);
          require.ensure([], () => {
            const {
              export_json_to_excel
            } = require("@/views/excel/Export2Excel.js");
            const tHeader = ["时间", "实际值", "6H预测值", "12H预测值", "6H误差率", "12H误差率", "6H平均误差率：" + this.avg6h + "%", "12H平均误差率" + this.avg12h + "%"]; // 导出的excel表头名信息
            const filterVal = [
              "datatm",
              "z",
              "z6h",
              "z12h",
              "forecast6herror",
              "forecast12herror"
            ]; // 导出的excel表头字段名，需要导出表格字段名
            const exceldata = that.formatJson(filterVal, data);

            export_json_to_excel(tHeader, exceldata, "预测分析报表"); // 导出的表格名称
            that.loading = false;
          });
        });

      },
      //格式转换
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      },
      handleSizeChange(val) {
        this.pagesize = val;
        this.getdata()
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.getdata()
      },
    },

    mounted() {

    }
  }
</script>

<style scoped>
  .timespan {
    line-height: 36px;
  }

  .timespan, .block {
    float: left;
  }

  .col-md-7 > button {
    margin-left: 15px;
  }

  .top, button {
    margin: 20px 0;
    white-space: nowrap;
  }

  .wcl {
    display: inline-block;
    margin-left: 15px;
  }

  .wcl > span:first-child {
    margin-right: 15px;
  }

  .bottom {
    text-align: center;
    margin: 20px 0;
  }
  @media (max-width: 767px) {
    .el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange.el-input__inner{
      width:320px;
    }
  }
</style>
