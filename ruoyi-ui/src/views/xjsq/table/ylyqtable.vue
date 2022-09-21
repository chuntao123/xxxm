<template>
  <div>
    <span>测站名称：</span>
    <el-input class="input" v-model="input" placeholder="请输入内容"></el-input>
    <el-button type="primary" @click="search"><i class="el-icon-download"></i> 查询</el-button>
    <!--<el-button type="primary"><i class="el-icon-bottom"></i> 导出</el-button>-->
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
        label="降雨量(mm)">
      </el-table-column>
      <el-table-column
        prop="waterQ"
        label="历时降雨(h)">
      </el-table-column>
      <el-table-column
        prop="waterTmdelay"
        label="累计降雨量">
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
