package cn.itbk.smallbox.api.param

import cn.itbk.smallbox.config.annotation.NoArg
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

/**
 * 登录参数接收
 *@author Hor
 *@create 2022/3/4 23:21
 */
@NoArg
data class EmailLoginParam(

//    @field:NotEmpty(message = "登录名不能为空")
//    @field:Pattern(
//        regexp = "^[A-Za-z0-9]+$",
//        message = "账号只允许英文和数字"
//    )

    @field:Email(message="邮箱的格式不正确")
    var email: String = "",
    @field:NotEmpty(message = "密码不能为空")
    var passwordMd5: String = "",
    var device: String? = "unknown"
)