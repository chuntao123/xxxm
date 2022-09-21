<template>
  <div class="index">
    <!-- 导航栏start -->
    <nav class="navbar navbar-default navbar-inverse" :class="navBarFixed === true ? 'navBarWrap' :''">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <i class="el-icon-s-home homeicon" @click="component = 'main_center'"></i>
          <span class="navbar-brand" title="西江航运水情大数据平台">西江航运水情大数据平台</span>
          <button type="button" @click="minshow" class="navbar-toggle collapsed" data-toggle="collapse"
                  data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar el-dropdown-link"></span>
            <span class="icon-bar el-dropdown-link"></span>
            <span class="icon-bar el-dropdown-link"></span>
          </button>
        </div>
        <el-collapse-transition>
        <div class="minnav" v-show="show">
          <ul v-for="item in routers" >
            <li v-for="children in item.children" :key="children.meta.title" v-if="!children.hidden"
                @click="handleCommand(children.path)">
              <span :class="children.meta.icon"></span>
              {{children.meta.title}}
            </li>
          </ul>
        </div>
        </el-collapse-transition>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li class="active" @click="component = 'main_center'">首页</li>
            <li v-for="item in routers">
              <el-dropdown trigger="click" @command="handleCommand">
                                <span class="el-dropdown-link">
                                    {{item.meta.title}}<i class="el-icon-caret-left" id="el-icon-caret-left"></i>
                                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item :command="children.path" :icon=children.meta.icon v-for="children in item.children"
                                    :key="children.meta.title" v-if="!children.hidden">{{children.meta.title}}
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
              <el-dropdown trigger="click" @command="login">
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <!-- <i class="iconfont" id="user" title="请输入账号">&#xe63e;</i> -->
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-user-solid" v-if="loginandout" command="login"> 登录</el-dropdown-item>
                  <el-dropdown-item icon="glyphicon glyphicon-off" v-else command="logout"> 注销</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>

            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <!-- 导航栏end -->
    <!-- 主题 start-->
    <main v-show="!show">

      <component :is="component" @ok="ok"></component>

    </main>
  </div>
</template>

<script>
  import {getRouters} from '@/api/menu.js'
  import {getToken} from '@/utils/auth'

  export default {
    name: "index",
    props: {
      list: {
        type: Array,
        default() {
          return [];
        },
      },
      color: {
        type: String,
        default: "#000",
      },
    },

    components: {
      main_center: () => import('@/components/user/main_center.vue'),
      Login: () => import('@/components/user/login.vue'),
      char: () => import('@/views/xjsq/chars/rtsq3in1_char.vue')

    },
    watch: {
      component: {
        handler() {
          this.getToken();
          if (this.loginandout == false) {
            this.menu()
          }
        }
      }
    },
    data() {
      return {
        routers: [],
        value: "1",
        show: false,
        component: "main_center",
        loginandout: "",
        navBarFixed: null,
        style:""
      };
    },
    methods: {
      watchScroll() {
        let scrollTop =
          window.pageYOffset ||
          document.documentElement.scrollTop ||
          document.body.scrollTop;
        //当滚动超过50时，实现吸顶效果（导航高度为50）
        if (scrollTop > 49) {
          this.navBarFixed = true
        } else {
          this.navBarFixed = false
        }
      },
      resize() {
        let width = document.body.clientWidth || document.documentElement.clientWidth;
        if (width > 768) {
          this.show = false;
        }
      },
      ok(route) {
        this.component = route;
      },
      //获取菜单
      menu() {
        getRouters().then(res => {
          this.routers = res.data;
        })
      },
      // 登入或登出
      login(command) {
        if (command == "login") {
          this.component = 'Login'
        } else {
          this.logout();
        }
      },
      async logout() {
        this.$confirm('确定注销并退出系统吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('LogOut').then(() => {
            location.href = '/';
          })
        }).catch(() => {
        });
      },
      // 获取用户token
      getToken() {
        if (getToken()) {
          this.loginandout = false
        } else {
          this.loginandout = true
        }

      },
      // 切换组件
      async handleCommand(path) {
        this.component = require('@/views/xjsq/' + path + '.vue').default
        this.show = false;
      },
      minshow() {
        if (this.loginandout) {
          this.component = 'Login'
        } else {
          this.show = !this.show
        }
      }
    },
    destroyed() {
      // 移除事件监听
      window.removeEventListener("scroll", this.watchScroll);
      window.removeEventListener("resize", this.resize);
    },


    mounted() {
      // 事件监听滚动条
      window.addEventListener("scroll", this.watchScroll);
      window.addEventListener("resize", this.resize);
      this.getToken();
      if (!this.loginandout) {
        this.menu();
      }
    },

  };
</script>
<style scoped>
  @import './../assets/styles/iconfont.css';
  @import './../assets/styles/index.css';
  @import './../assets/styles/global.css';
</style>
<style lang="less">
</style>



