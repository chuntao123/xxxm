<template>
  <div>
    <span>测站名称：</span>
    <el-input class="input" v-model="input" placeholder="请输入内容"></el-input>
    <el-button type="primary" @click="search"><i class="el-icon-search"></i> 查询</el-button>
    <el-button type="primary" @click="upload"><i class="el-icon-download"></i> 导出</el-button>
    <el-table
      :data="info"
      stripe
      class="eltable"
      style="width: 100%">
      <el-table-column
        prop="stid"
        label="测站ID"
      >
      </el-table-column>
      <el-table-column
        prop="stnm"
        label="测站名称"
      >
      </el-table-column>
      <el-table-column
        prop="waterZ"
        label="水位(m)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.waterDatatm}}</span>
            <div slot="reference" >
              {{ scope.row.waterZ }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="waterQ"
        label="流量(m³/s)">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.waterDatatm}}</span>
            <div slot="reference" >
              {{ scope.row.waterQ }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="waterTmdelay"
        label="水势">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="left">
            <p>更新时间</p>
            <span>{{scope.row.waterDatatm}}</span>
            <div slot="reference" >
              {{ scope.row.waterTmdelay }}
            </div>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "swsqtable",
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
    methods:{
      search(){
        this.info=this.data.filter(item=>item.stnm.indexOf(this.input)>-1);
      },
      //表格数据写入excel
      upload() {
        var that = this;
        require.ensure([], () => {
          const {
            export_json_to_excel
          } = require("@/views/excel/Export2Excel.js");
          const tHeader = ["测站ID", "测站名称", "水位(m)", "流量(m³/s)", "水势", "更新时间"]; // 导出的excel表头名信息
          const filterVal = [
            "stid",
            "stnm",
            "waterZ",
            "waterQ",
            "waterTmdelay",
            "waterDatatm"
          ]; // 导出的excel表头字段名，需要导出表格字段名
          const list = that.info;
          const data = that.formatJson(filterVal, list);

          export_json_to_excel(tHeader, data, "水文水情"); // 导出的表格名称
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
