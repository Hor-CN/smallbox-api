package cn.itbk.smallbox.config.satoken

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 *@author Hor
 *@create 2022/3/4 22:55
 */
@Configuration
@EnableWebMvc
class SaTokenConfigure : WebMvcConfigurer {

    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    override fun addInterceptors(registry: InterceptorRegistry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(SaAnnotationInterceptor())
            .addPathPatterns(listOf("/**"))
    }
}