package cn.itbk.smallbox.service.impl

import cn.dev33.satoken.secure.SaSecureUtil
import cn.dev33.satoken.stp.StpUtil
import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil
import cn.itbk.smallbox.api.param.UserUpdateParam
import cn.itbk.smallbox.common.Constants
import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.domain.User
import cn.itbk.smallbox.mapper.UserMapper
import cn.itbk.smallbox.service.IUserService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author Hor
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-03-02 17:50:31
 */
@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), IUserService {

    override fun login(loginName: String, passWord: String, device: String?): String {
        val queryWrapper = QueryWrapper<User>().apply {
            eq("login_name", loginName)
        }
        getBaseMapper().selectOne(queryWrapper)?.let {
            if (StpUtil.isDisable(it.userId)) {
                return Constants.USER_DISABLE + StpUtil.getDisableTime(it.userId)
            }
            if (it.passwordMd5 == SaSecureUtil.md5BySalt(passWord, it.salt)) {
                StpUtil.login(it.userId, device)
                StpUtil.getSession()["user"] = it
                return StpUtil.getTokenValue()
            }
        }
        return ResultEnum.LOGIN_ERROR.message
    }

    override fun registerUser(loginName: String, passwordMd5: String): ResultEnum {
        if (getBaseMapper().exists(QueryWrapper<User>().eq("login_name", loginName))) {
            return ResultEnum.USER_ALREADY_EXISTS
        }
        val salt = SecureUtil.md5(loginName + Arrays.toString(RandomUtil.randomBytes(6)))
        val user = User().apply {
            this.loginName = loginName
            this.avatar = Constants.USER_AVATAR
            this.passwordMd5 = SaSecureUtil.md5BySalt(passwordMd5,salt)
            this.nickName = Constants.USER_NAME
            this.salt = salt
        }
        if (getBaseMapper().insert(user) > 0){
            return ResultEnum.USER_SUCCESS
        }
        return ResultEnum.USER_FAIL
    }

    /**
     * 用户信息修改
     *
     * @param updateParam
     * @param userId
     * @return Boolean
     */
    override fun updateUserInfo(updateParam: UserUpdateParam, userId: Long): Boolean {
        val user = getBaseMapper().selectById(userId) ?: return false
        user.avatar = updateParam.avatar
        user.nickName = updateParam.nickName
        user.introduce = updateParam.introduce
        if (!updateParam.passwordMd5.isNullOrEmpty() && !updateParam.newPasswordMd5.isNullOrEmpty()){
            val oldPasswordMd5 = SaSecureUtil.md5BySalt(updateParam.passwordMd5,user.salt)
            if (!oldPasswordMd5.equals(user.passwordMd5)){
                return false
            }
            user.passwordMd5 = SaSecureUtil.md5BySalt(updateParam.newPasswordMd5,user.salt)
        }
        if(getBaseMapper().updateById(user) > 0) {
            StpUtil.getSession()["user"] = user
            return true
        }
        return false
    }
}