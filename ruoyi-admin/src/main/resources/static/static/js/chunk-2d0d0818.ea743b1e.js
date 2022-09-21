(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d0818"],{"67ef":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0,"label-width":"68px"}},[a("el-form-item",{attrs:{label:"登录地址",prop:"ipaddr"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入登录地址",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.ipaddr,callback:function(t){e.$set(e.queryParams,"ipaddr",t)},expression:"queryParams.ipaddr"}})],1),a("el-form-item",{attrs:{label:"用户名称",prop:"userName"}},[a("el-input",{staticStyle:{width:"240px"},attrs:{placeholder:"请输入用户名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.userName,callback:function(t){e.$set(e.queryParams,"userName",t)},expression:"queryParams.userName"}})],1),a("el-form-item",{attrs:{label:"状态",prop:"status"}},[a("el-select",{staticStyle:{width:"240px"},attrs:{placeholder:"登录状态",clearable:""},model:{value:e.queryParams.status,callback:function(t){e.$set(e.queryParams,"status",t)},expression:"queryParams.status"}},e._l(e.dict.type.sys_common_status,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",{attrs:{label:"登录时间"}},[a("el-date-picker",{staticStyle:{width:"240px"},attrs:{"value-format":"yyyy-MM-dd",type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.dateRange,callback:function(t){e.dateRange=t},expression:"dateRange"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),a("el-row",{staticClass:"mb8",attrs:{gutter:10}},[a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["monitor:logininfor:remove"],expression:"['monitor:logininfor:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini",disabled:e.multiple},on:{click:e.handleDelete}},[e._v("删除")])],1),a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["monitor:logininfor:remove"],expression:"['monitor:logininfor:remove']"}],attrs:{type:"danger",plain:"",icon:"el-icon-delete",size:"mini"},on:{click:e.handleClean}},[e._v("清空")])],1),a("el-col",{attrs:{span:1.5}},[a("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["monitor:logininfor:export"],expression:"['monitor:logininfor:export']"}],attrs:{type:"warning",plain:"",icon:"el-icon-download",size:"mini"},on:{click:e.handleExport}},[e._v("导出")])],1),a("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"tables",attrs:{data:e.list,"default-sort":e.defaultSort},on:{"selection-change":e.handleSelectionChange,"sort-change":e.handleSortChange}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),a("el-table-column",{attrs:{label:"访问编号",align:"center",prop:"infoId"}}),a("el-table-column",{attrs:{label:"用户名称",align:"center",prop:"userName","show-overflow-tooltip":!0,sortable:"custom","sort-orders":["descending","ascending"]}}),a("el-table-column",{attrs:{label:"登录地址",align:"center",prop:"ipaddr",width:"130","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"登录地点",align:"center",prop:"loginLocation","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"浏览器",align:"center",prop:"browser","show-overflow-tooltip":!0}}),a("el-table-column",{attrs:{label:"操作系统",align:"center",prop:"os"}}),a("el-table-column",{attrs:{label:"登录状态",align:"center",prop:"status"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("dict-tag",{attrs:{options:e.dict.type.sys_common_status,value:t.row.status}})]}}])}),a("el-table-column",{attrs:{label:"操作信息",align:"center",prop:"msg"}}),a("el-table-column",{attrs:{label:"登录日期",align:"center",prop:"loginTime",sortable:"custom","sort-orders":["descending","ascending"],width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.parseTime(t.row.loginTime)))])]}}])})],1),a("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.queryParams.pageNum,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"pageNum",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1)},r=[],o=a("5530"),i=(a("4e82"),a("d81d"),a("b775"));function l(e){return Object(i["a"])({url:"/monitor/logininfor/list",method:"get",params:e})}function s(e){return Object(i["a"])({url:"/monitor/logininfor/"+e,method:"delete"})}function c(){return Object(i["a"])({url:"/monitor/logininfor/clean",method:"delete"})}var u={name:"Logininfor",dicts:["sys_common_status"],data:function(){return{loading:!0,ids:[],multiple:!0,showSearch:!0,total:0,list:[],dateRange:[],defaultSort:{prop:"loginTime",order:"descending"},queryParams:{pageNum:1,pageSize:10,ipaddr:void 0,userName:void 0,status:void 0}}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.loading=!0,l(this.addDateRange(this.queryParams,this.dateRange)).then((function(t){e.list=t.rows,e.total=t.total,e.loading=!1}))},handleQuery:function(){this.queryParams.pageNum=1,this.getList()},resetQuery:function(){this.dateRange=[],this.resetForm("queryForm"),this.$refs.tables.sort(this.defaultSort.prop,this.defaultSort.order),this.handleQuery()},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.infoId})),this.multiple=!e.length},handleSortChange:function(e,t,a){this.queryParams.orderByColumn=e.prop,this.queryParams.isAsc=e.order,this.getList()},handleDelete:function(e){var t=this,a=e.infoId||this.ids;this.$modal.confirm('是否确认删除访问编号为"'+a+'"的数据项？').then((function(){return s(a)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))},handleClean:function(){var e=this;this.$modal.confirm("是否确认清空所有登录日志数据项？").then((function(){return c()})).then((function(){e.getList(),e.$modal.msgSuccess("清空成功")})).catch((function(){}))},handleExport:function(){this.download("monitor/logininfor/export",Object(o["a"])({},this.queryParams),"logininfor_".concat((new Date).getTime(),".xlsx"))}}},d=u,m=a("2877"),p=Object(m["a"])(d,n,r,!1,null,null,null);t["default"]=p.exports}}]);