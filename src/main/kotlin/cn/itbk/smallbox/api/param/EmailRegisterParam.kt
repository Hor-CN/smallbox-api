package cn.itbk.smallbox.api.param

import cn.itbk.smallbox.config.annotation.NoArg
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

/**
 *
 *@author Hor
 *@create 2022/4/20 19:52
 */
@NoArg
data class EmailRegisterParam(
    @field:Email(message="邮箱的格式不正确")
    var email: String = "",
    @field:NotEmpty(message = "密码不能为空")
    var passwordMd5: String = "",
    @field:NotEmpty(message = "验证码不能为空")
    var code: String = "",
)