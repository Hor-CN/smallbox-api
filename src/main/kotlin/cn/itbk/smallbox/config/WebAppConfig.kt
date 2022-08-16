package cn.itbk.smallbox.config

import cn.itbk.smallbox.config.interceptor.ResponseResultInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 *
 *@author Hor
 *@create 2022/3/2 21:50
 */
@Configuration
class WebAppConfig : WebMvcConfigurer {
    // SpringMVC 需要手动添加拦截器
    override fun addInterceptors(registry: InterceptorRegistry) {
        val interceptor = ResponseResultInterceptor()
        registry.addInterceptor(interceptor)
        super.addInterceptors(registry)
    }
}