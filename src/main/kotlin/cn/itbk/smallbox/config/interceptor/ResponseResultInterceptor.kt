package cn.itbk.smallbox.config.interceptor

import cn.itbk.smallbox.config.annotation.ResponseResult
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.reflect.Method
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *
 *@author Hor
 *@create 2022/3/2 21:47
 */
@Component
class ResponseResultInterceptor : HandlerInterceptor {

    companion object {
        //标记名称
        private const val RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN"
    }

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val clazz = handler.beanType
            val method: Method = handler.method
            // 判断是否在类对象上添加了注解
            if (clazz.isAnnotationPresent(ResponseResult::class.java)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(
                    RESPONSE_RESULT_ANN, clazz.getAnnotation(
                        ResponseResult::class.java
                    )
                )
            } else if (method.isAnnotationPresent(ResponseResult::class.java)) {
                request.setAttribute(
                    RESPONSE_RESULT_ANN, method.getAnnotation(
                        ResponseResult::class.java
                    )
                )
            }
        }
        return true
    }


}