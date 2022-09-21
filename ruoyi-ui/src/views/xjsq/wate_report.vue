<template>
  <div class="container fade-in-left" v-loading="loading">
    <div class="row">
      <div class="col-md-12 one">
        <div class="row zd">
          <div class="col-md-3">
            <span>站点：</span>
            <div class="block">
              <el-cascader
                v-model="queryData.zd"
                :options="zdoptions"
                :props="props"
                collapse-tags
                clearable></el-cascader>
            </div>
          </div>
          <div class="col-md-3">
            <span>条件：</span>
            <div class="block">
              <el-cascader
                v-model="queryData.tj"
                :options="tjoptions"
                :props="props"
                collapse-tags
                clearable></el-cascader>
            </div>
          </div>
          <div class="col-md-3">
            <span>类型：</span>
            <div class="block">
              <el-cascader
                @change="changetime"
                v-model="queryData.lx"
                :options="lxoptions"
              ></el-cascader>
            </div>
          </div>
          <div class="col-md-3">
            <span>图表：</span>
            <div class="block">
              <el-cascader
                v-model="queryData.tb"
                :options="tboptions"
                :props="props"
                collapse-tags
                clearable></el-cascader>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-12 two">
        <div class="row">
          <div class="col-md-8">
            <span>时间：</span>
            <div class="block">
              <el-date-picker
                v-model="value"
                type="datetimerange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                align="right">
              </el-date-picker>
            </div>
          </div>

        </div>
      </div>

      <div class="col-md-12 three">
        <div class="row">
          <div class="col-md-3 col-xs-12">
            <span>数据：</span>
            <el-input v-model="input" placeholder="请输入数据关键词" style="width: 219px"></el-input>
          </div>
          <div class="col-md-4 buttonbox">
            <el-button type="primary" @click="query"><i class="el-icon-search"></i>查询</el-button>
            <el-button type="primary" @click="upload"><i class="el-icon-download"></i>导出</el-button>
            <el-button type="primary" @click="email"><i class="el-icon-message"></i>邮箱导出</el-button>
          </div>
        </div>
      </div>

    </div>
    <div class="row table">
      <div class="col-md-12">
        <el-table
          :data="Data"
          border
          style="width: 100%">
          <el-table-column :prop="item.senid" :label="item.label" :key="item.senid" v-for="item in header">
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
  import {menulistRtsq3in1, speciallistRtsq3in1, bblistRtsq3in1} from "@/api/system/rtsq3in1.js"
  import moment from "moment";
  import qs from 'qs'

  export default {
    name: "wate_report",
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
          }, {
            text: '最近一年',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 365);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        props: {multiple: true},
        input: "",//模糊查询关键字
        value: [],//时间范围
        zdoptions: [],
        tjoptions: [
          {
            value: 'max',
            label: '最大值',
          },
          {
            value: 'min',
            label: '最小值',
          },
          {
            value: 'avg',
            label: '平均值',
          },
          {
            value: 'total',
            label: '累计值',
          }
        ],
        lxoptions: [
          {
            value: '1',
            label: '一天',
          },
          {
            value: '7',
            label: '七天',
          },
          {
            value: '30',
            label: '一月',
            children: [{
              value: 'integer',
              label: '整点值显示'
            },
              {
                value: 'dayavg',
                label: '日平均显示'
              }, {
                value: 'monthavg',
                label: '月平均显示'
              }
            ]
          },
          {
            value: '90',
            label: '三月',
            children: [{
              value: 'integer',
              label: '整点值显示'
            },
              {
                value: 'dayavg',
                label: '日平均显示'
              }, {
                value: 'monthavg',
                label: '月平均显示'
              }
            ]
          },
          {
            value: '365',
            label: '一年',
            children: [{
              value: 'integer',
              label: '整点值显示'
            },
              {
                value: 'dayavg',
                label: '日平均显示'
              }, {
                value: 'monthavg',
                label: '月平均显示'
              }
            ]
          },
        ],
        tboptions: [{
          value: 'fxt',
          label: '分析图'
        }],
        queryData: { //查询条件
          zd: [],
          tj: [],
          lx: "",
          tb: []
        },
        Data: [],//表格数据
        menudata: [],//菜单
        header: [],//表格头
        show: false,
        currentPage: 1,//页码
        pagesize: 10,//展示的数量
        total: 0,//总数据数
        list: [],//最大、最小等特殊值集合
        zd: [],//要查询的站点集合
        loading: false,
        url: ""
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
      menu() {
        menulistRtsq3in1().then(res => {
          this.menudata = res.rows;
          var yjzd = Array.from((this.menudata), ({csname}) => csname);
          yjzd = new Set(yjzd);//去重
          yjzd = Array.from(yjzd); //重新转化为数组
          var arr = [];
          this.menudata.forEach(item => {
            const parent = yjzd.find(cur => cur === item.csname);
            if (parent) {
              let index = arr.findIndex(cur => cur.label === parent);
              if (index > -1) {
                arr[index].children.push({label: item.senname, value: item.senid.toString()})
              } else {
                const obj = {
                  label: item.csname,
                  children: [{label: item.senname, value: item.senid.toString()}]
                };
                arr.push(obj)
              }
            }
          });
          this.zdoptions = arr;
        })
      },
      changetime(value) {
        this.value = [];
        let end = moment().format('yyyy-MM-DD HH:mm:ss');
        let start = moment().add(-parseInt(value[0]), 'day').format('yyyy-MM-DD HH:mm:ss');
        this.value.push(start, end);
      },
      query() {
        if (this.queryData.zd.length === 0) {
          return this.$modal.msgError("请选择站点！");
        }
        if (this.value.length === 0) {
          return this.$modal.msgError("请选择时间！");
        }
        this.loading = true;
        // 写入表头start
        this.zd = [];
        this.queryData.zd.forEach(item => {
          this.zd.push(item[1])
        });
        this.header = [{senid: "time", label: "时间/数据"}];
        this.menudata.forEach(item => {
          let index = this.zd.findIndex(cur => cur === item.senid.toString());
          if (index > -1) {
            this.header.push({senid: item.senid.toString(), label: item.senname})
          }
        });
        // 写入表头end

        // 写入最大值、最小值等特殊值
        if (this.queryData.tj.length !== 0) {
          this.speciallist()
        } else {
          this.getData()
        }
      },
      speciallist() {
        this.list = [];
        let params = {
          zd: this.zd,
          starttime: moment(this.value[0]).format('yyyy-MM-DD HH:mm:ss'),
          endtime: moment(this.value[1]).format('yyyy-MM-DD HH:mm:ss'),
        };
        params = qs.stringify(params, {indices: false});
        speciallistRtsq3in1(params).then(res => {
          var data = res.rows;
          this.queryData.tj.forEach((item, i) => {
            let a = this.tjoptions.findIndex(cur => cur.value === item[0]);
            if (a > -1) {
              this.list.push({time: this.tjoptions[a].label});
              data.forEach(items => {
                this.$set(this.list[i], items.senid, items[this.tjoptions[a].value])
              })
            }
          });
          this.getData()
        })
      },
      getData() {
        this.loading = true;
        let params = {
          starttime: moment(this.value[0]).format('yyyy-MM-DD HH:mm:ss'),
          endtime: moment(this.value[1]).format('yyyy-MM-DD HH:mm:ss'),
        };
        let url = "";
        let totalurl = '';
        let a = [];
        let b = " INNER JOIN ";
        let c = [];
        let d = " WHERE ";
        let e = [];
        let f = " AND ";
        let g = "";
        let Page, size = "";
        if (this.currentPage === 1) {
          Page = this.currentPage - 1;
          size = this.pagesize - this.list.length;
        } else {
          Page = (this.currentPage - 1) * this.pagesize - this.list.length;
          size = this.pagesize;
        }
        this.zd.forEach((item, i) => {
          if (this.zd.length === 1) {
            url = "SELECT t0d.SENID senid0,DATE_FORMAT(t0d.TIME,'%Y-%m-%d %H:%i:%s') time,t0d.V '" + item + "'  FROM wz_rtsq3in1_clean t0d where senid = " + item + " AND t0d.TIME BETWEEN '" + params.starttime + "' AND '" + params.endtime + "' AND extract(minute from time) = 0 AND t0d.TIME like '%" + this.input + "%' ORDER BY t0d.TIME desc "
            this.url = url;
            url = url + "LIMIT " + Page + "," + size;
            totalurl = "SELECT count(*) total  FROM wz_rtsq3in1_clean t0d where senid = " + item + " AND t0d.TIME BETWEEN '" + params.starttime + "' AND '" + params.endtime + "' AND extract(minute from time) = 0 AND t0d.TIME like '%" + this.input + "%' ORDER BY t0d.TIME desc"
          } else {
            a.push("wz_rtsq3in1_clean t" + i + "d");
            if (i < this.zd.length - 1) {
              c.push(" ON t0d.TIME = t" + (i + 1) + "d.TIME ");
            }
            e.push("t" + i + "d.SENID = " + item);
            if (i === 0) {
              g = "t0d.SENID senid0,DATE_FORMAT(t0d.TIME,'%Y-%m-%d %H:%i:%s') time,t0d.V '" + item + "',"
            }
            if (i > 0 && i < this.zd.length - 1) {
              g = g + "t" + i + "d.SENID senid" + i + ",t" + i + "d.V '" + item + "',"
            }
            if (i === this.zd.length - 1) {
              g = g + "t" + i + "d.SENID senid" + i + ",t" + i + "d.V '" + item + "'"
            }

          }

        });
        if (url === "") {
          a.forEach((item, i) => {
            if (i === 0) {
              url = url + item + b + a[i + 1] + c[i]
            }
            if (a.length - 1 > i && i > 0) {
              url = url + b + a[i + 1] + c[i]
            }
          });
          e.forEach((item, i) => {
            if (i === 0) {
              url = url + d + item
            } else {
              url = url + f + item
            }

          });
          totalurl = "SELECT count(*) total FROM " + url + " AND t0d.TIME BETWEEN '" + params.starttime + "' AND '" + params.endtime + "' AND extract(minute from t0d.TIME) = 0 AND t0d.TIME like '%" + this.input + "%'ORDER BY t0d.TIME desc";
          url = "SELECT " + g + " FROM " + url + " AND t0d.TIME BETWEEN '" + params.starttime + "' AND '" + params.endtime + "' AND extract(minute from t0d.TIME) = 0 AND t0d.TIME like '%" + this.input + "%' ORDER BY t0d.TIME desc ";
          this.url = url;
          url = url + "LIMIT " + Page + "," + size
        }

        bblistRtsq3in1({url: totalurl}).then(res => {
          this.total = this.list.length + res[0].total;
          bblistRtsq3in1({url: url}).then(res1 => {
            let list = [];
            var data = res1;
            if (this.currentPage === 1 && this.list.length !== 0) {
              list = this.list.concat(data);
              this.Data = list;
            } else {
              this.Data = data;
            }
            this.loading = false;
          })
        })

      },
      upload() {
        if (this.Data.length === 0) {
          return this.$modal.msgError("请先查询数据！");
        }
        this.loading = true;
        var that = this;
        bblistRtsq3in1({url: this.url}).then(res => {

          var data = res;
          data = this.list.concat(data);
          require.ensure([], () => {
            const {
              export_json_to_excel
            } = require("@/views/excel/Export2Excel.js");
            const tHeader = []; // 导出的excel表头名信息
            const filterVal = ["time"]; // 导出的excel表头字段名，需要导出表格字段名
            this.header.forEach(item => {
              tHeader.push(item.label)
            });
            this.zd.forEach(item => {
              filterVal.push(item)
            });
            const exceldata = that.formatJson(filterVal, data);

            export_json_to_excel(tHeader, exceldata, "水情报表"); // 导出的表格名称
            that.loading = false;
          });
        });

      },
      //格式转换
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      },
      email() {
        if (this.Data.length === 0) {
          return this.$modal.msgError("请先查询数据！");
        }
        this.$prompt('请输入邮箱', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '邮箱格式不正确'
        }).then(({value}) => {
          this.loading = true;
          var that = this;
          bblistRtsq3in1({url: this.url}).then(res => {

            var data = res;
            data = this.list.concat(data);

            require.ensure([], () => {
              const {
                export_email_to_excel
              } = require("@/views/excel/Export2Excel.js");
              const tHeader = []; // 导出的excel表头名信息
              const filterVal = ["time"]; // 导出的excel表头字段名，需要导出表格字段名
              this.header.forEach(item => {
                tHeader.push(item.label)
              });
              this.zd.forEach(item => {
                filterVal.push(item)
              });
              const exceldata = that.formatJson(filterVal, data);

              export_email_to_excel(tHeader, exceldata, "水情报表", value); // 导出的表格名称
              that.loading = false;
            });
          });
        })

      },
      handleSizeChange(val) {
        this.pagesize = val;
        this.getData()
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.getData()
      },

    },

    mounted() {
      this.menu()
    }
  }
</script>

<style scoped>
  .block {
    display: inline-block;
  }

  /*第一行选择框*/
  .one {
    margin-top: 15px;
  }

  /*第二行选择框*/
  .two {
    margin: 15px 0;
  }

  .table {
    margin-top: 15px;
  }

  .bottom {
    text-align: center;
    margin: 20px 0;
  }

  @media (max-width: 767px) {
    .zd > div:nth-child(2) {
      margin: 15px 0;
    }

    .zd > div:nth-child(4) {
      margin-top: 15px;
    }

    .buttonbox {
      margin-top: 50px;
    }

    .el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange.el-input__inner {
      width: 320px;
    }
  }

</style>
