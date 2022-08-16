package cn.itbk.smallbox.service.impl

import cn.dev33.satoken.dao.SaTokenDaoRedisJackson
import cn.dev33.satoken.util.SaFoxUtil
import cn.hutool.captcha.generator.RandomGenerator
import cn.hutool.core.util.RandomUtil
import cn.hutool.extra.mail.MailUtil
import cn.hutool.extra.template.TemplateConfig
import cn.hutool.extra.template.TemplateUtil
import cn.itbk.smallbox.service.IEmailService
import freemarker.template.Template
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import java.util.*
import javax.annotation.Resource
import kotlin.collections.HashMap


/**
 *
 *@author Hor
 *@create 2022/4/15 10:18
 */

@Service
class EmailServiceImpl : IEmailService {

    @Resource
    private lateinit var saRedis: SaTokenDaoRedisJackson


    /**
     * 发送验证码邮件
     * @param email
     * @return boolean
     */
    override fun sendEmailCode(email: String): Boolean {
        var code = saRedis.get(email)
        // 从redis缓存中尝试获取验证码
        if (code.isNullOrEmpty()) {
            // 如果未获取到验证码
            // 获取随机code
            code = SaFoxUtil.getRandomString(4)
            // 保存验证码 5 分钟
            saRedis.set(email, code, 60 * 5L)
        }
        // 设置模板传入数据
        val model: MutableMap<String, Any> = HashMap()
        model["code"] = code
        // 获取模板内容
        val template = TemplateUtil.createEngine(TemplateConfig( "", TemplateConfig.ResourceMode.CLASSPATH)).getTemplate("sendCode.html")
        val content = template.render(model)
        return try {
            MailUtil.send(email, "匣子邮箱验证", content, true)
            true
        } catch (e: Exception) {
            false
        }
    }


}