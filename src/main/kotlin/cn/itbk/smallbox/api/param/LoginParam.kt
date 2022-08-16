package cn.itbk.smallbox.api.param

import cn.itbk.smallbox.config.annotation.NoArg
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

/**
 *
 *@author Hor
 *@create 2022/4/14 19:58
 */
@NoArg
data class LoginParam (

    @field:NotEmpty(message = "账号不能为空")
    var loginName: String,
    @field:NotEmpty(message = "密码不能为空")
    var passwordMd5: String = "",
    var device: String? = "unknown"
)