package cn.itbk.smallbox.service

import cn.itbk.smallbox.api.param.UserUpdateParam
import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.domain.User
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @author Hor
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2022-03-02 17:50:31
 */
interface IUserService : IService<User?> {

    /**
     * 登录
     *
     * @param loginName 用户名
     * @param passWord 密码
     * @param device 设备
     * @return String
     */
    fun login(loginName: String, passWord: String, device: String?): String

    /**
     * 用户注册
     *
     * @param loginName
     * @param passwordMd5
     * @return ResultEnum
     */
    fun registerUser(loginName: String, passwordMd5: String) : ResultEnum
    /**
     * 用户信息修改
     *
     * @param updateParam
     * @param userId
     * @return Boolean
     */
    fun updateUserInfo(updateParam: UserUpdateParam, userId: Long): Boolean

}