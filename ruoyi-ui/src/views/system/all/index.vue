<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="summary">
        <el-input
          v-model="queryParams.summary"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点ID" prop="csid">
        <el-input
          v-model="queryParams.csid"
          placeholder="请输入站点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点名字" prop="csname">
        <el-input
          v-model="queryParams.csname"
          placeholder="请输入站点名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="值" prop="bssw">
        <el-input
          v-model="queryParams.bssw"
          placeholder="请输入值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="bsswtime">
        <el-date-picker clearable
          v-model="queryParams.bsswtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="值" prop="bxsw">
        <el-input
          v-model="queryParams.bxsw"
          placeholder="请输入值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="bxswtime">
        <el-date-picker clearable
          v-model="queryParams.bxswtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="值" prop="ckll">
        <el-input
          v-model="queryParams.ckll"
          placeholder="请输入值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="cklltime">
        <el-date-picker clearable
          v-model="queryParams.cklltime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="值" prop="rkll">
        <el-input
          v-model="queryParams.rkll"
          placeholder="请输入值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="rklltime">
        <el-date-picker clearable
          v-model="queryParams.rklltime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="值" prop="qcyg">
        <el-input
          v-model="queryParams.qcyg"
          placeholder="请输入值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="qcygtime">
        <el-date-picker clearable
          v-model="queryParams.qcygtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:all:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:all:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:all:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:all:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="allList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="summary" />
      <el-table-column label="站点ID" align="center" prop="csid" />
      <el-table-column label="站点名字" align="center" prop="csname" />
      <el-table-column label="值" align="center" prop="bssw" />
      <el-table-column label="更新时间" align="center" prop="bsswtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bsswtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值" align="center" prop="bxsw" />
      <el-table-column label="更新时间" align="center" prop="bxswtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bxswtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值" align="center" prop="ckll" />
      <el-table-column label="更新时间" align="center" prop="cklltime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cklltime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值" align="center" prop="rkll" />
      <el-table-column label="更新时间" align="center" prop="rklltime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rklltime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="值" align="center" prop="qcyg" />
      <el-table-column label="更新时间" align="center" prop="qcygtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qcygtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:all:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:all:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改VIEW对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="summary">
          <el-input v-model="form.summary" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="站点ID" prop="csid">
          <el-input v-model="form.csid" placeholder="请输入站点ID" />
        </el-form-item>
        <el-form-item label="站点名字" prop="csname">
          <el-input v-model="form.csname" placeholder="请输入站点名字" />
        </el-form-item>
        <el-form-item label="值" prop="bssw">
          <el-input v-model="form.bssw" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="更新时间" prop="bsswtime">
          <el-date-picker clearable
            v-model="form.bsswtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值" prop="bxsw">
          <el-input v-model="form.bxsw" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="更新时间" prop="bxswtime">
          <el-date-picker clearable
            v-model="form.bxswtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值" prop="ckll">
          <el-input v-model="form.ckll" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="更新时间" prop="cklltime">
          <el-date-picker clearable
            v-model="form.cklltime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值" prop="rkll">
          <el-input v-model="form.rkll" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="更新时间" prop="rklltime">
          <el-date-picker clearable
            v-model="form.rklltime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="值" prop="qcyg">
          <el-input v-model="form.qcyg" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="更新时间" prop="qcygtime">
          <el-date-picker clearable
            v-model="form.qcygtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">${subTable.functionName}信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddWzCs">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteWzCs">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="wzCsList" :row-class-name="rowWzCsIndex" @selection-change="handleWzCsSelectionChange" ref="wzCs">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="站点名字" prop="csname" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.csname" placeholder="请输入站点名字" />
            </template>
          </el-table-column>
          <el-table-column label="经度" prop="ceslo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ceslo" placeholder="请输入经度" />
            </template>
          </el-table-column>
          <el-table-column label="纬度" prop="cntla" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cntla" placeholder="请输入纬度" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="state" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.state" placeholder="请输入状态" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="summary" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.summary" placeholder="请输入$comment" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAll, getAll, delAll, addAll, updateAll } from "@/api/system/all";

export default {
  name: "All",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedWzCs: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // VIEW表格数据
      allList: [],
      // ${subTable.functionName}表格数据
      wzCsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        summary: null,
        csid: null,
        csname: null,
        bssw: null,
        bsswtime: null,
        bxsw: null,
        bxswtime: null,
        ckll: null,
        cklltime: null,
        rkll: null,
        rklltime: null,
        qcyg: null,
        qcygtime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        csid: [
          { required: true, message: "站点ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询VIEW列表 */
    getList() {
      this.loading = true;
      listAll(this.queryParams).then(response => {
        this.allList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        summary: null,
        csid: null,
        csname: null,
        bssw: null,
        bsswtime: null,
        bxsw: null,
        bxswtime: null,
        ckll: null,
        cklltime: null,
        rkll: null,
        rklltime: null,
        qcyg: null,
        qcygtime: null
      };
      this.wzCsList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.summary)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加VIEW";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const summary = row.summary || this.ids
      getAll(summary).then(response => {
        this.form = response.data;
        this.wzCsList = response.data.wzCsList;
        this.open = true;
        this.title = "修改VIEW";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.wzCsList = this.wzCsList;
          if (this.form.summary != null) {
            updateAll(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAll(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const summarys = row.summary || this.ids;
      this.$modal.confirm('是否确认删除VIEW编号为"' + summarys + '"的数据项？').then(function() {
        return delAll(summarys);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowWzCsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddWzCs() {
      let obj = {};
      obj.csname = "";
      obj.ceslo = "";
      obj.cntla = "";
      obj.state = "";
      obj.summary = "";
      this.wzCsList.push(obj);
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteWzCs() {
      if (this.checkedWzCs.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const wzCsList = this.wzCsList;
        const checkedWzCs = this.checkedWzCs;
        this.wzCsList = wzCsList.filter(function(item) {
          return checkedWzCs.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleWzCsSelectionChange(selection) {
      this.checkedWzCs = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/all/export', {
        ...this.queryParams
      }, `all_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
