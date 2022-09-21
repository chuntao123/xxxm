<template>
  <span class="el-icon-loading loading" aria-hidden="true" v-if="loading"></span>
  <div class="container fade-in-left" v-else>
    <div class="row ">
      <h1>Welcome</h1>
      <!-- 登录窗口start -->
      <div class="col-md-6 col-md-offset-3 col-xs-12 col-sm-8 col-sm-offset-2  col-lg-6 col-lg-offset-3 login">
        <h2>登录平台</h2>
        <div class="row">
          <div class="col-md-12  col-xs-12 col-sm-12 col-lg-12">
            <div class="mian">
              <span class="iconfont .usericon" title="用户名" aria-hidden="true">&#xe65e;</span>
              <span class="iconfont passwordicon" title="密码" aria-hidden="true">&#xe620;</span>
              <span class="iconfont codeicon" title="验证码" aria-hidden="true">&#xe624;</span>
              <img :src="codeUrl" alt="" @click="getcode">
              <input type="text" placeholder="请输入账号" @keydown.enter="login" v-model="loginForm.username" maxlength="20"
                     id="input1">
              <input type="password" placeholder="请输入密码" @keydown.enter="login" v-model="loginForm.password"
                     maxlength="50" id="input2">
              <input type="text" placeholder="请输入验证码" @keydown.enter="login" v-model="loginForm.code" maxlength="4"
                     id="input3">
            </div>
            <span :class="loading_icon ? 'el-icon-loading' : 'glyphicon glyphicon-download'" @click="login" id="green"
                  aria-hidden="true"></span>
            <div class="remember_me">
              <el-checkbox v-model="loginForm.rememberMe">记住我的账号</el-checkbox>
            </div>
            <p @click="forget"> 忘记了 账号 或 密码？<i class="el-icon-top-right"></i></p>
            <p @click="forget">没有 账号？ 立即创建您的 账号。<i class="el-icon-top-right"></i></p>
          </div>
        </div>
      </div>
    </div>
    <!-- 登录窗口end -->
  </div>
</template>

<script>
  import {getCodeImg, login} from '@/api/login.js'
  import Cookies from "js-cookie";
  import {encrypt, decrypt} from '@/utils/jsencrypt'

  export default {
    name: "login",
    data() {
      return {
        loginForm: {
          username: "",
          password: "",
          rememberMe: false,
          code: "",
          uuid: ""
        },
        // 验证码链接
        codeUrl: "",
        loading_icon: false,
        // 验证码开关
        captchaEnabled: true,
        route: "main_center",
        loading: true
      };
    },
    watch: {
      loginForm: {
        handler(newVal, oldVal) {
          if (this.loading == false) {
            if (this.loginForm.username != "" && this.loginForm.password != "" && this.loginForm.code != "") {
              var bottom = document.querySelector("#green");
              bottom.style.color = 'green';
              bottom.style.cursor = 'pointer'
            } else {
              var bottom = document.querySelector("#green");
              bottom.style.color = 'rgb(146,146,146)';
              bottom.style.cursor = 'not-allowed'
            }
          }
        },
        immediate: false,
        deep: true // 可以深度检测到 person 对象的属性值的变化
      },
    },


  methods: {

    // 忘记密码
    forget()
    {
      this.$modal.alert("请联系管理员！");
    }
  ,
    // 获取随机数
    selectFrom(lowerValue, upperValue)
    {
      var choices = upperValue - lowerValue + 1;
      return Math.floor(Math.random() * choices + lowerValue);
    }
  ,
    // 获取Cookie
    getCookie()
    {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe');
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
        code: ""
      };
    }
  ,

    // 获取验证码
    getcode()
    {
      getCodeImg().then((res) => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.loginForm.uuid = res.uuid;
      })
    }
  ,
    // 登录
    login()
    {
      this.loading_icon = true;
      this.$store.dispatch("Login", this.loginForm).then(() => {
        if (this.loginForm.rememberMe) {
          Cookies.set("username", this.loginForm.username, {expires: 30});
          Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
          Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
        } else {
          Cookies.remove("username");
          Cookies.remove("password");
          Cookies.remove('rememberMe');
        }
        let num = this.selectFrom(500, 2000);
        setTimeout(() => {
          this.$emit("ok", this.route);
        }, num)

      }).catch(() => {
        this.loading_icon = false;
        this.getcode()
      });
    }
  }
  ,
  mounted() {
    setTimeout(() => {
      this.loading = false;
    }, this.selectFrom(500, 1000));

    this.getcode();
    this.getCookie()
  }
  ,

  }
  ;
</script>
<style scoped>
  @import './../../assets/styles/iconfont.css';
  @import './../../assets/styles/login.css';
  @import './../../assets/styles/global.css';
</style>
<style lang="less">
</style>
