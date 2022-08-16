package cn.itbk.smallbox.domain

import java.util.*

/**
 * 拓展插件表
 * @TableName applet
 */
data class Applet (
    /**
     * 插件id
     */
    var id: Long? = null,
    /**
     * 小程序标识
     */
    var appId: String? = null,
    /*
    * 应用图标
    */
    var appIcon: String? = null,
    /**
     * 应用名称
     */
    var name: String? = null,
    /**
     * 应用描述
     */
    var description: String? = null,
    /*
    * 作者
    */
    var userId: Long? = null,
    /**
     * 下载数量
     */
    var downloadNum: Any? = null,
    /**
     * 评论数量
     */
    var commentNum: Any? = null,
    /**
     * 点赞数量
     */
    var likeNum: Any? = null,
    /**
     * 下载地址
     */
    var downloadUrl: String? = null,
    /**
     * 创建时间
     */
    var createTime: Date? = null,
    /**
     * 更新时间
     */
    var updateTime: Date? = null

)