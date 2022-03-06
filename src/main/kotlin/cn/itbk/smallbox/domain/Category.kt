package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.util.*

/**
 * 父类表
 * @TableName category
 */
class Category (

    /**
     * 分类ID
     */
    @TableId(
        type = IdType.AUTO
    )
    var categoryId: Long? = null,
    /**
     * 分类名称
     */
    var categoryName: String? = null,
    /**
     * 排序值(字段越大越靠前)
     */
    var categoryRank: Int? = null,
    /**
     * 创建时间
     */
    var createTime: Date? = null

)