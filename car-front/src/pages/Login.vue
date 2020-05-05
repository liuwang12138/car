<template>
    <div class="login-wrap">
        <div class="ms-login" v-if="loginVisible">
            <div class="ms-title">登录</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="请输入账户名">
                        <el-button slot="prepend">账户</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="param.password">
                        <el-button slot="prepend">密码</el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitLogin()">登录</el-button>
                </div>
            </el-form>
            <a class="login-tips" @click="toRegester">没有账户？去注册</a>
        </div>

        <div class="ms-login" v-if="regesterVisible">
            <div class="ms-title">注册</div>
            <el-form :model="rParam" :rules="rules1" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="rParam.username" placeholder="请输入账户">
                        <el-button slot="prepend">账户</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="rParam.password">
                        <el-button slot="prepend">密码</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="realName">
                    <el-input v-model="rParam.realName" placeholder="请输入姓名">
                        <el-button slot="prepend">姓名</el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input v-model="rParam.phone" placeholder="请输入电话">
                        <el-button slot="prepend">电话</el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitRegester()">注册</el-button>
                </div>
            </el-form>
            <a class="login-tips" @click="toLogin">已有账户？去登录</a>
        </div>
    </div>
</template>

<script>
import { login, register } from '../api/index';
export default {
    data: function() {
        return {
            param: {
                username: '',
                password: '',
            },
            rParam: {
                username: '',
                password: '',
                realName: '',
                phone: ''
            },
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
            },
            rules1: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                realName: [{ required: true, message: '请输入真实姓名', trigger: 'blur' }],
                phone: [{ required: true, message: '请输入联系方式', trigger: 'blur' }],
            },
            loginVisible: true,
            regesterVisible: false
        };
    },
    methods: {
        toLogin() {
            this.regesterVisible = false;
            this.loginVisible = true;
        },
        toRegester() {
            this.loginVisible = false;
            this.regesterVisible = true;
        },
        submitLogin() {
            login(this.param).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.$message.success('登录成功');
                    localStorage.setItem('ms_username', this.param.username);
                    this.$router.push('/Home');
                } else {
                    this.$message.error(res.data.message);
                    console.log('error submit!!');
                    return false;
                }
            }).catch((err) => {
                this.$message.error('登录失败');
            });
        },
        submitRegester() {
            register(this.rParam).then(res => {
                if(res.data.code == 201) {
                    console.log(res);
                    this.$message.success('注册成功');
                    this.regesterVisible = false;
                    this.loginVisible = true;
                } else {
                    this.$message.error(res.data.message);
                    console.log('error submit!!');
                    return false;
                }
            }).catch((err) => {
                this.$message.error('注册失败');
            });
        }
    },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../assets/slide_pc2.png);
    background-size: 100% 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
</style>