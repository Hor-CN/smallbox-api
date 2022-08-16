package cn.itbk.smallbox.domain

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 *
 * @TableName user_third_auth
 */
@TableName(value = "user_third_auth")
class UserThirdAuth : Serializable {
    /**
     * 第三方用户唯一标识
     */
    @TableId
    var openid: String? = null
    /**
     * ID
     */
    var authId: Long? = null
    /**
     * 第三方平台标识(qq、wechat...)
     */
    var loginType: String? = null
    /**
     * 第三方获取的access_token,校验使用
     */
    var accessToken: String? = null

    companion object {
        @TableField(exist = false)
        private val serialVersionUID = 1L
    }
}