package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 *
 * @TableName user_local_auth
 */
@TableName(value = "user_local_auth")
class UserLocalAuth : Serializable {
    /**
     * 认证id，自增id
     */
    @TableId(type = IdType.AUTO)
    var authId: Long? = null
    /**
     * 用户ID
     */
    var userId: Long? = null
    /**
     * 用户邮箱
     */
    var email: String? = null
    /**
     * 用户密码
     */
    var passwordMd5: String? = null
    /**
     * 密码加密盐
     */
    var salt: String? = null

    companion object {
        @TableField(exist = false)
        private val serialVersionUID = 1L
    }
}