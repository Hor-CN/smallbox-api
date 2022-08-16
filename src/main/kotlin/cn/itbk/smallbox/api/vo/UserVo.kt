package cn.itbk.smallbox.api.vo

import cn.dev33.satoken.stp.SaTokenInfo
import com.baomidou.mybatisplus.annotation.TableField
import java.util.*

/**
 *
 *@author Hor
 *@create 2022/3/5 20:06
 */
data class UserVo(
    var userId: Long? = null,
    /**
     * 头像
     */
    var avatarImg: String? = null,
    /**
     * 昵称
     */
    var nikeName: String? = null,
    /**
     * 简介
     */
    var introduce: String? = null,
    /**
     * 是否是开发者
     */
    var developer: Boolean? = null,
    /**
     * 创建时间
     */
    var createTime: Date? = null,
    @TableField(exist = false)
    var tokenInfo: SaTokenInfo? = null
)
