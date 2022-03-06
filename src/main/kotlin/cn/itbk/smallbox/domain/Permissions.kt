package cn.itbk.smallbox.domain

import java.io.Serializable
import java.lang.StringBuilder
import java.util.*

/**
 * 权限表
 * @TableName permissions
 */
class Permissions {

    var id: Int? = null
    /**
     * 权限
     */
    var perms: String? = null
    /**
     * 权限名称
     */
    var name: String? = null

    var createTime: Date? = null

}