package cn.itbk.smallbox.api.param

import cn.itbk.smallbox.config.annotation.NoArg
import javax.validation.constraints.NotEmpty

/**
 * 第三方登录
 *@author Hor
 *@create 2022/4/14 21:05
 */
@NoArg
data class ThirdLoginParam(
    @field:NotEmpty(message = "loginType不能为空")
    var login_type : String,
    @field:NotEmpty(message = "token不能为空")
    var access_token: String,
)
