package cn.itbk.smallbox.api.param

import cn.itbk.smallbox.config.annotation.NoArg
import javax.validation.constraints.NotEmpty

/**
 *
 *@author Hor
 *@create 2022/3/5 16:19
 */
@NoArg
data class UserRegisterParam(

    @field:NotEmpty(message = "登录名不能为空")
    var loginName: String = "",
    @field:NotEmpty(message = "密码不能为空")
    var passwordMd5: String = "",

)
