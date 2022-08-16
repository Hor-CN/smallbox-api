package cn.itbk.smallbox.service

import cn.itbk.smallbox.api.param.ThirdLoginParam
import cn.itbk.smallbox.domain.UserLocalAuth
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @author Hor
 * @description 针对表【user_local_auth】的数据库操作Service
 * @createDate 2022-04-14 20:05:24
 */
interface UserLocalAuthService : IService<UserLocalAuth?> {

    /**
     * 账号与密码登录
     * @param loginName 用户账号
     * @param password 密码
     * @param device? 设备名称
     */
    fun login(loginName: String, password: String, device: String?): String

    /**
     * 邮箱与密码登录
     * @param loginEmail 邮箱
     * @param password 密码
     * @param device? 设备名称
     */
    fun emailLogin(loginEmail: String, password: String, device: String?) : String

    /**
     * 第三方登录
     */
    fun thirdLogin( openid:String, login_type: String, access_token: String) : String

    /**
     * 邮箱注册账号
     *
     */
    fun emailRegisterUser(email: String, password: String) : Boolean


    /** 注册邮箱是否存在
     * @param email
     * @return Boolean
     */
    fun registerEmailExist(email: String) : Boolean


    /**
     * 判断账号是否存在
     * @param userid
     * @return Boolean
     */
    fun userNameExits(userid: String): Boolean
}