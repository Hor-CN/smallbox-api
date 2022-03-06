package cn.itbk.smallbox.api.vo

import java.util.*

/**
 *
 *@author Hor
 *@create 2022/3/5 20:06
 */
data class UserVo(
    var userId: Long? = null,
    /**
     * 用户头像链接
     */
    var avatar: String? = null,
    /**
     * 用户邮箱
     */
    var email: String? = null,
    /**
     * 登陆名称
     */
    var loginName: String? = null,
    /**
     * 用户昵称
     */
    var nickName: String? = null,
    /**
     * 个性签名
     */
    var introduce: String? = null,
    /**
     * 开发者标识字段(0-未成为开发者 1-已成为开发者)
     */
    var developerFlag: Int? = null,
    /**
     * 注册时间
     */
    var createTime: Date? = null
)
