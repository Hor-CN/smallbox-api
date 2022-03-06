package cn.itbk.smallbox.api

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.StpUtil
import cn.itbk.smallbox.api.param.UserLoginParam
import cn.itbk.smallbox.api.param.UserRegisterParam
import cn.itbk.smallbox.api.param.UserUpdateParam
import cn.itbk.smallbox.api.vo.UserVo
import cn.itbk.smallbox.common.Constants
import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.common.SmallBoxException
import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.domain.User
import cn.itbk.smallbox.service.impl.UserServiceImpl
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import org.springframework.beans.BeanUtils
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
@RequestMapping("/api")
class UserAPI {

    @Resource
    lateinit var userServiceImpl: UserServiceImpl


    @GetMapping("/user")
    fun getUser(@RequestParam id: String?): Result<Any> {
        val user: User = userServiceImpl.baseMapper.selectById(id) ?: return  ResultGenerator.fail(ResultEnum.USER_NO_EXISTS.message)
        val userVo = UserVo()
        BeanUtils.copyProperties(user,userVo)
        return ResultGenerator.success(data = userVo)
    }


    @PostMapping("/user/login")
    fun login(@RequestBody @Valid loginParam: UserLoginParam): Result<Any> {
        val loginResult = userServiceImpl.login(loginParam.loginName, loginParam.passwordMd5, loginParam.device)
        return if (loginResult.length == Constants.TOKEN_LENGTH) {
            ResultGenerator.success(ResultEnum.LOGIN_SUCCESS.message, loginResult)
        } else ResultGenerator.fail(loginResult)
    }

    @SaCheckLogin
    @PostMapping("/user/logout")
    fun logout(): Result<Any> {
        StpUtil.logout()
        return ResultGenerator.success(message = ResultEnum.LOGIN_LOGOUT.message)
    }

    @PostMapping("/user/register")
    fun register(@RequestBody @Valid registerParam: UserRegisterParam): Result<Any> {
        return when(val registerResult = userServiceImpl.registerUser(registerParam.loginName,registerParam.passwordMd5)){
            ResultEnum.USER_SUCCESS -> ResultGenerator.success(message = registerResult.message)
            else -> throw SmallBoxException(registerResult.message)
        }
    }

    @GetMapping("/user/info")
    @SaCheckLogin
    fun getUserDetail(): Result<Any> {
        val userVo = UserVo()
        BeanUtils.copyProperties(StpUtil.getSession().get("user"),userVo)
        return ResultGenerator.success(data = userVo)
    }

    @SaCheckLogin
    @PutMapping("/user/info")
    fun updateInfo(@RequestBody updateParam: UserUpdateParam): Result<Any> {
        return if (userServiceImpl.updateUserInfo(updateParam,StpUtil.getLoginIdAsLong()))
            ResultGenerator.success(message = ResultEnum.USER_UPDATE_SUCCESS.message)
        else ResultGenerator.fail(message = ResultEnum.USER_UPDATE_FAIL.message)
    }

    @SaCheckLogin
    @RequestMapping("/user/tokenInfo")
    fun tokenInfo(): Result<Any> {
        return ResultGenerator.success(data = StpUtil.getTokenInfo())
    }

}