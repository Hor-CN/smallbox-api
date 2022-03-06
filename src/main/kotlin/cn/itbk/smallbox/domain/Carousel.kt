package cn.itbk.smallbox.domain

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
    var imagePath: String? = null,
    /**
     * 点击跳转地址
     */
    var url: String? = null,
    /**
     * 轮播图类型(0-网页链接 1-拓展详情)
     */
    var type: Int? = null,
    /**
     * 创建时间
     */
    var createTime: Date? = null
)