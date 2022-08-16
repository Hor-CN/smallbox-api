package cn.itbk.smallbox.api

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.dao.SaTokenDaoRedisJackson
import cn.dev33.satoken.stp.StpUtil
import cn.hutool.http.HttpRequest
import cn.hutool.json.JSONUtil
import cn.itbk.smallbox.api.param.*
import cn.itbk.smallbox.api.vo.UserVo
import cn.itbk.smallbox.common.Constants
import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.common.SmallBoxException
import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.service.impl.EmailServiceImpl
import cn.itbk.smallbox.service.impl.UserLocalAuthServiceImpl
import cn.itbk.smallbox.service.impl.UserServiceImpl
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import com.alibaba.fastjson.JSON
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.BeanUtils
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.validation.Valid

/**
 * 用户操作API
 *@author Hor
 *@create 2022/3/4 22:48
 */
@RestController
@ResponseResult
@EnableAsync
@RequestMapping("/api")
class UserAPI {

    @Resource
    lateinit var userServiceImpl: UserServiceImpl

//    @GetMapping("/user")
//    fun getUser(@RequestParam id: String?): Result<Any> {
//        val user: User = userServiceImpl.baseMapper.selectById(id) ?: throw SmallBoxException(ResultEnum.USER_NO_EXISTS.message)
//        val userVo = UserVo()
//        BeanUtils.copyProperties(user,userVo)
//        return ResultGenerator.success(data = userVo)
//    }

    @Resource
    lateinit var localAuthService: UserLocalAuthServiceImpl

    @Resource
    lateinit var emailServiceImpl: EmailServiceImpl

    @Resource
    private lateinit var saRedis: SaTokenDaoRedisJackson


    // 注册用户
    @PostMapping("/user/register")
    fun register(@RequestBody @Valid loginParam: EmailRegisterParam): Result<Any> {
        // 1. 判断邮箱用户是否存在
        if(localAuthService.registerEmailExist(loginParam.email)) {
            throw SmallBoxException("邮箱已存在")
        }
        // 2. 验证验证码是否正确
        val code = saRedis.get(loginParam.email)
        if (code.isNullOrEmpty() || !code.equals(loginParam.code)) {
            throw SmallBoxException("无效验证码")
        }
        // 3. 创建用户
        return if (localAuthService.emailRegisterUser(loginParam.email, loginParam.passwordMd5)) {
            saRedis.delete(loginParam.email)
            ResultGenerator.success("注册成功")
        }else {
            ResultGenerator.fail("注册失败")
        }
    }


    /**
     * 发送邮箱验证码
     * @param email 目标邮箱
     */
    @Async("threadPool")
    @RequestMapping("/emailCode")
    fun sendEmailCode(@RequestParam email: String) {
        emailServiceImpl.sendEmailCode(email)
    }

    // 账号密码登录
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginParam: LoginParam): Result<Any> {
        val loginResult = localAuthService.login(loginParam.loginName, loginParam.passwordMd5, loginParam.device)
        return if (loginResult.length == Constants.TOKEN_LENGTH) {
            val userVo = UserVo()
            BeanUtils.copyProperties(StpUtil.getSession().get("user"),userVo)
            userVo.tokenInfo = StpUtil.getTokenInfo()
            ResultGenerator.success(ResultEnum.LOGIN_SUCCESS.message, userVo)
        } else ResultGenerator.fail(loginResult)
    }

    // 邮箱登录
    @PostMapping("/email/login")
    fun emailLogin(@RequestBody @Valid loginParam: EmailLoginParam): Result<Any> {
        val loginResult = localAuthService.emailLogin(loginParam.email, loginParam.passwordMd5, loginParam.device)
        return if (loginResult.length == Constants.TOKEN_LENGTH) {
            val userVo = UserVo()
            BeanUtils.copyProperties(StpUtil.getSession().get("user"),userVo)
            userVo.tokenInfo = StpUtil.getTokenInfo()
            ResultGenerator.success(ResultEnum.LOGIN_SUCCESS.message, userVo)
        } else ResultGenerator.fail(loginResult)
    }

    // 第三方登录
    @PostMapping("/third/login")
    fun thirdLogin(@RequestBody @Valid thirdLoginParam: ThirdLoginParam): Result<Any> {
        val result: String = HttpRequest.get("https://openmobile.qq.com/oauth2.0/me?access_token=${thirdLoginParam.access_token}")
            .timeout(2000) //超时，毫秒
            .execute().body().replace("callback(","").replace(");","")
        val json = JSONUtil.parseObj(result)
        json["error"]?.let {
            return ResultGenerator.fail(json["error_description"].toString())
        }
        val openid = json["openid"].toString()
        val loginResult = localAuthService.thirdLogin(openid,thirdLoginParam.login_type, thirdLoginParam.access_token)
        return if(loginResult.length == Constants.TOKEN_LENGTH) {
            val userVo = UserVo()
            BeanUtils.copyProperties(StpUtil.getSession().get("user"),userVo)
            userVo.tokenInfo = StpUtil.getTokenInfo()
            ResultGenerator.success(ResultEnum.LOGIN_SUCCESS.message, userVo)
        }else ResultGenerator.fail(loginResult)
    }

    @SaCheckLogin
    @PostMapping("/user/logout")
    fun logout(): Result<Any> {
        StpUtil.logout()
        return ResultGenerator.success(message = ResultEnum.LOGIN_LOGOUT.message)
    }

    @GetMapping("/user/info")
    @SaCheckLogin
    fun getUserDetail(): Result<Any> {
        val userVo = UserVo()
        BeanUtils.copyProperties(StpUtil.getSession().get("user"),userVo)
        userVo.tokenInfo = StpUtil.getTokenInfo()
        return ResultGenerator.success(data = userVo)
    }

//    @SaCheckLogin
//    @PutMapping("/user/info")
//    fun updateInfo(@RequestBody updateParam: UserUpdateParam): Result<Any> {
//        return if (userServiceImpl.updateUserInfo(updateParam,StpUtil.getLoginIdAsLong()))
//            ResultGenerator.success(message = ResultEnum.USER_UPDATE_SUCCESS.message)
//        else ResultGenerator.fail(message = ResultEnum.USER_UPDATE_FAIL.message)
//    }

    @SaCheckLogin
    @RequestMapping("/user/tokenInfo")
    fun tokenInfo(): Result<Any> {
        return ResultGenerator.success(data = StpUtil.getTokenInfo())
    }

}