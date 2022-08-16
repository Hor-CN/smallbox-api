package cn.itbk.smallbox.api.vo

import cn.itbk.smallbox.domain.Carousel
import cn.itbk.smallbox.dto.GoodsDto

/**
 *
 *@author Hor
 *@create 2022/3/8 16:23
 */
data class IndexVo (

    var carousels: List<Carousel?>? = null,
    var goods: List<GoodsDto>? = null
)