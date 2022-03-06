package cn.itbk.smallbox.api.param

/**
 *
 *@author Hor
 *@create 2022/3/5 20:46
 */
data class UserUpdateParam(
    /**
     * 用户头像链接
     */
    var avatar: String? = null,
    /**
     * 用户昵称
     */
    var nickName: String? = null,
    /**
     * 个性签名
     */
    var introduce: String? = null,
    /**
     * MD5加密后的密码
     */
    var passwordMd5: String? = null,
    /**
     * MD5加密后的新密码
     */
    var newPasswordMd5: String? = null,
)
