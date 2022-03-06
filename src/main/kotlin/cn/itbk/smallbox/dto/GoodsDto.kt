package cn.itbk.smallbox.dto

import cn.itbk.smallbox.domain.Applet

/**
 *
 *@author Hor
 *@create 2022/3/8 20:33
 */
data class GoodsDto (
    var title: String? = null,
    var value: List<Applet>? = null
)