<template>
  <div id="login-wrapper">
    <div class="login-panel-wrapper">
      <el-row>
        <el-col :span="16" id="left">
          <el-row type="flex" class="row-bg" justify="center">
            <h2>
              企业版
              <span>找新的员工</span>
            </h2>
          </el-row>
          <el-row class="form-wrapper">
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="名称">
                <el-input v-model="formLabelAlign.cname"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input type="password" v-model="formLabelAlign.cpassword"></el-input>
              </el-form-item>
            </el-form>
          </el-row>
          <el-row type="flex" class="row-bg" justify="center">
            <el-button type="primary" @click="test">登录</el-button>
          </el-row>
        </el-col>
        <el-col :span="8" id="right">
          <div>
            新用户？
            <router-link to="/fregiste">点击注册</router-link>
          </div>
          <div>
            <el-button @click="back" type="text" class="button">返回</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="bg-1"></div>
    <div class="bg-2"></div>
  </div>
</template>

<script>
export default {
  name: "firmlogin",
  data() {
    return {
      labelPosition: "right",
      formLabelAlign: {
        cname: "",
        cpassword: ""
      }
    };
  },
  methods: {
    //----返回
    back() {
      this.$router.back(-1);
    },
    test: function(event) {
      console.log(this.formLabelAlign);
      this.axios({
        method: "post",
        url: "authFirm",
        data: this.formLabelAlign
      })
        .then(response => {
          if (response.data) {
            this.$message("登录成功，即将跳转...");
            let firm = {
              id: response.data.cid,
              name: response.data.cname,
              email: response.data.cemail,
              auth: 1
              //0->user,1->label
            };
            localStorage.setItem("auth", JSON.stringify(firm));
            setTimeout(() => {
              this.$router.push({ path: "/" });
            }, 3000);
          } else {
            this.$message.error("密码和用户名不一致");
          }
        })
        .catch(error => {
          this.$message.error("网络错误");
        });
    }
  }
};
</script>

<style>
.form-wrapper {
  margin: 20px;
}
#login-wrapper {
  overflow: hidden;
}
.bg-1,
.bg-2 {
  width: 100%;
  height: 360px;
}
.bg-1 {
  background: #3f617f;
}
#left,
#right {
  padding: 5%;
}
.login-panel-wrapper {
  position: absolute;
  width: 50%;
  height: 50%;
  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background: white;
  border: solid #bbbbbb 0.3px;
}
</style>
