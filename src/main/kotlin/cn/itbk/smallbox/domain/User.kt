package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.util.*


/**
 * 用户表
 * @TableName user
 */
data class User(
    /**
     * 用户ID
     */
    @TableId(
        type = IdType.AUTO
    )
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
     * MD5加密后的密码
     */
    var passwordMd5: String? = null,
    /**
     * 密码加密盐值
     */
    var salt: String? = null,
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