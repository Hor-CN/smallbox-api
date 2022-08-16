package cn.itbk.smallbox.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

/**
 * 轮播图
 * @TableName carousel
 */
data class Carousel (
     /**
     * 轮播图主键ID
     */
    var id: Int? = null,
    /**
     * 标题
     */
    var title: String? = null,
    /**
     * 图片链接
     */
    var carouselUrl: String? = null,
    /**
     * 点击跳转地址
     */
    var redirectUrl: String? = null,
    /**
     * 轮播图类型(0-网页链接 1-插件详情)
     */
    var type: Int? = null,
    /**
     * 创建时间
     */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var createTime: Date? = null,
    /**
     * 修改时间
     */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     var updateTime: Date? = null,
    /**
     * 创建轮播图的用户
     */
    var createUser: Long? = null,
    /**
     * 修改轮播图的用户
     */
    var updateUser: Long? = null,
    /**
     * 轮播图的排序顺序
     */
    var carouselRank: Int? = null,
)