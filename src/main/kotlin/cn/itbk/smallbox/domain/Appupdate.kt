package cn.itbk.smallbox.domain

import java.util.*

/**
 * 应用更新
 * @TableName appupdate
 */
data class Appupdate (

    var id: Int? = null,

    /**
     *
     */
    var apkname: String? = null,

    /**
     * 软件更新文件链接
     */
    var apkurl: String? = null,

    /**
     * APK大小
     */
    var apksize: Int? = null,

    /**
     * 软件版本
     */
    var apkversioncode: Int? = null,

    /**
     * 软件名称
     */
    var apkversionname: String? = null,

    /**
     * 软件更新详情
     */
    var apkdescription: String? = null,

    /**
     * 是否强制更新
     */
    var forcedupgrade: Boolean? = null,
    var createTime: Date? = null
)