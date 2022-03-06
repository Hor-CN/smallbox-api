package cn.itbk.smallbox.util

import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.config.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

/**
 *
 *@author Hor
 *@create 2022/3/2 21:10
 */
@NoArg
@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class Result<T>(
    //业务码code
    var code: Int,
    //返回提示信息
    var message: String,
    //数据结果
    var data: T?,
) : Serializable