package cn.itbk.smallbox.domain

import java.io.Serializable
import java.lang.StringBuilder
import java.util.*

/**
 * 角色标识
 * @TableName role
 */
data class Role (
    /**
     *
     */
    var id: Int? = null,
    /**
     * 角色名称
     */
    var name: String? = null,
    /**
     * 角色介绍
     */

    var note: String? = null,

    var createTime: Date? = null

)