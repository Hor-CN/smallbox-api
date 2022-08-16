package cn.itbk.smallbox.service.impl

import cn.dev33.satoken.secure.SaSecureUtil
import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaFoxUtil
import cn.hutool.crypto.SecureUtil
import cn.itbk.smallbox.common.Constants
import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.domain.User
import cn.itbk.smallbox.domain.UserLocalAuth
import cn.itbk.smallbox.domain.UserThirdAuth
import cn.itbk.smallbox.mapper.UserLocalAuthMapper
import cn.itbk.smallbox.service.UserLocalAuthService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * @author Hor
 * @description 针对表【user_local_auth】的数据库操作Service实现
 * @createDate 2022-04-14 20:05:24
 */
@Service
class UserLocalAuthServiceImpl : ServiceImpl<UserLocalAuthMapper, UserLocalAuth>(), UserLocalAuthService {


    @Resource
    lateinit var userServiceImpl: UserServiceImpl

    @Resource
    lateinit var userThirdAuthServiceImpl: UserThirdAuthServiceImpl

    // 账号与密码登录
    override fun login(loginName: String, password: String, device: String?): String {
        return localLogin("user_id",loginName,password,device)
    }

    // 邮箱登录
    override fun emailLogin(loginEmail: String, password: String, device: String?): String {
        return localLogin("email",loginEmail,password,device)
    }

    /**
     * 第三方登录
     */
    override fun thirdLogin(openid: String, login_type: String, access_token: String): String {
        // 1. 是否已经绑定用户,已绑定直接登录
        userThirdAuthServiceImpl.getById(openid)?.let {
            // 获取id
            val userid = getBaseMapper().selectById(it.authId)!!.userId
            if (StpUtil.isDisable(userid)) {
                return Constants.USER_DISABLE + StpUtil.getDisableTime(userid)
            }
            StpUtil.login(userid)
            StpUtil.getSession()["user"] = userServiceImpl.getById(userid)
            return StpUtil.getTokenValue()
        }
        //
        // 2. 否则直接注册新账户
        // 新增的用户的实体类
        val user: User = User().apply {
            avatarImg = Constants.USER_AVATAR
            nikeName = Constants.USER_NAME
        }
        userServiceImpl.baseMapper.insert(user)
        val id = user.userId!!
        // 用户密码盐
        val salt = SecureUtil.md5(id.toString() + SaFoxUtil.getRandomString(10))
        val localUser: UserLocalAuth = UserLocalAuth().apply {
            this.userId = id
            this.salt = salt
            this.passwordMd5 = SaSecureUtil.md5BySalt(openid,salt)
        }
        getBaseMapper().insert(localUser)
        val thirdUser =  UserThirdAuth().apply {
            this.openid = openid
            this.authId = localUser.authId
            this.loginType = login_type
            this.accessToken = access_token
        }
        if(userThirdAuthServiceImpl.save(thirdUser)) {
            if (StpUtil.isDisable(localUser.userId)) {
                return Constants.USER_DISABLE + StpUtil.getDisableTime(localUser.userId)
            }
            StpUtil.login(localUser.userId)
            StpUtil.getSession()["user"] = userServiceImpl.getById(localUser.userId)
            return StpUtil.getTokenValue()
        }
        return ResultEnum.LOGIN_ERROR.message
    }




    /**
     * 邮箱注册账号
     *@param email
     * @param password
     */
    override fun emailRegisterUser(email: String,password: String) : Boolean {
        // 新增的用户的实体类
        val user: User = User().apply {
            avatarImg = Constants.USER_AVATAR
            nikeName = Constants.USER_NAME
        }

        userServiceImpl.baseMapper.insert(user)
        val id = user.userId!!
        // 用户密码盐
        val salt = SecureUtil.md5(id.toString() + SaFoxUtil.getRandomString(10))
        val localUser: UserLocalAuth = UserLocalAuth().apply {
            this.userId = id
            this.email = email
            this.salt = salt
            this.passwordMd5 = SaSecureUtil.md5BySalt(password,salt)
        }
        return getBaseMapper().insert(localUser) > 0
    }


    /** 注册邮箱是否存在
     * @param email
     * @return Boolean
     */
    override fun registerEmailExist(email: String): Boolean {
        val queryWrapper:QueryWrapper<UserLocalAuth> = QueryWrapper()
        queryWrapper.eq("email",email)
        return getBaseMapper().selectOne(queryWrapper) != null
    }

    /**
     * 判断账号是否存在
     * @param userid
     * @return Boolean
     */
    override fun userNameExits(userid: String): Boolean {
        val queryWrapper: QueryWrapper<UserLocalAuth> = QueryWrapper()
        queryWrapper.lambda().eq(UserLocalAuth::userId,userid)
        return getBaseMapper().selectOne(queryWrapper) != null
    }


    fun localLogin(loginType: String,loginName: String, password: String, device: String?): String {
        val queryWrapper = QueryWrapper<UserLocalAuth>().apply {
            eq(loginType, loginName)
        }
        getBaseMapper().selectOne(queryWrapper)?.let {
            // 邮箱用户存在
            // 判断用户是否被封禁
            if (StpUtil.isDisable(it.userId)) {
                return Constants.USER_DISABLE + StpUtil.getDisableTime(it.userId)
            }
            // 判断密码是否正确
            if (it.passwordMd5 == SaSecureUtil.md5BySalt(password, it.salt)) {
                StpUtil.login(it.userId, device)
                StpUtil.getSession()["user"] = userServiceImpl.getById(it.userId)
                return StpUtil.getTokenValue()
            }
        }
        return ResultEnum.LOGIN_ERROR.message
    }

}