package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.util.*

/**
 *
 * @TableName user
 */
@TableName(value = "user")
class User : Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    var userId: Long? = null
    /**
     * 头像
     */
    var avatarImg: String? = null
    /**
     * 昵称
     */
    var nikeName: String? = null
    /**
     * 简介
     */
    var introduce: String? = null
    /**
     * 是否是开发者
     */
    var developer: Boolean? = null
    /**
     * 创建时间
     */
    var createTime: Date? = null
    companion object {
        @TableField(exist = false)
        private val serialVersionUID = 1L
    }
}