<template>
  <div>
    <span>测站名称：</span>
    <el-input  class="input" v-model="input" placeholder="请输入内容"></el-input>
    <el-button type="primary" @click="search"><i class="el-icon-search"></i> 查询</el-button>
    <el-button type="primary" @click="upload"><i class="el-icon-download"></i> 导出</el-button>
    <el-table
      :data="info"
      id="list"
      stripe
      class="eltable"
      style="width: 100%">
      <el-table-column
        prop="csid"
        label="测站ID"
      >
      </el-table-column>
      <el-table-column
        prop="csname"
        label="测站名称"
      >
      </el-table-column>
      <el-table-column
        prop="bssw"
        label="坝上水位(m)"

      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.bsswtime}}</span>
            <div slot="reference" >
              {{ scope.row.bssw }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="bxsw"
        label="坝下水位(m)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.bxswtime}}</span>
            <div slot="reference" >
              {{ scope.row.bxsw }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="ckll"
        label="出库流量(m³/s)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.cklltime}}</span>
            <div slot="reference" >
              {{ scope.row.ckll }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="rkll"
        label="入库流量(m³/s)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.rklltime}}</span>
            <div slot="reference" >
              {{ scope.row.rkll }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="qcyg"
        label="功率(MW)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.qcygtime}}</span>
            <div slot="reference" >
              {{ scope.row.qcyg }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "czsqtable",
    props: {
      data: {
        type: Array,
        defalut: {}
      }
    },
    data() {
      return {
        input: "",
        info: this.data,
      }
    },
    methods: {
      search() {
        this.info = this.data.filter(item => item.csname.indexOf(this.input) > -1);
      },

      //表格数据写入excel
      upload() {
        var that = this;
        require.ensure([], () => {
          const {
            export_json_to_excel
          } = require("@/views/excel/Export2Excel.js");
          const tHeader = ["测站ID", "测站名称", "坝上水位(m)", "坝上水位数据更新时间", "坝下水位(m)", "坝下水位数据更新时间", "出库流量(m³/s)", "出库流量更新时间", "入库流量(m³/s)", "入库流量更新时间", "功率(MW)", "功率更新时间",]; // 导出的excel表头名信息
          const filterVal = [
            "csid",
            "csname",
            "bssw",
            "bsswtime",
            "bxsw",
            "bxswtime",
            "ckll",
            "cklltime",
            "rkll",
            "rklltime",
            "qcyg",
            "qcygtime"
          ]; // 导出的excel表头字段名，需要导出表格字段名
          const list = that.info;
          const data = that.formatJson(filterVal, list);

          export_json_to_excel(tHeader, data, "船闸水情"); // 导出的表格名称
        });
      },
      //格式转换
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      }

    },
    mounted() {

    }
  }
</script>

<style scoped>
   .eltable{
     margin-top: 15px;
   }
  .input{
    width: 200px;
  }
  button{
    margin-left: 20px;
  }
  @media (max-width: 767px) {
    .input{
      width: 35%;
    }
    button {
      margin-left: 10px;
      width: 20%;
    }



  }

</style>
