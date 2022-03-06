package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * 首页
 * @TableName index
 */
@TableName(value = "index")
data class Index (
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    /**
     * 分类ID
     */
    var categoryId: Long? = null,
    /**
     * 插件ID
     */
    var appletId: Long? = null

)