package cn.itbk.smallbox.common

import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.util.HttpContextUtil
import cn.itbk.smallbox.util.Result
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import javax.servlet.http.HttpServletRequest


/**
 *
 *@author Hor
 *@create 2022/3/2 21:37
 */
@ControllerAdvice
class ResponseResultHandler : ResponseBodyAdvice<Any> {
    companion object {
//        private val log = LoggerFactory.getLogger(ResponseResultHandler::class.java)
        private const val RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN"

    }

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        val request: HttpServletRequest = HttpContextUtil.getHttpServletRequest()
        val responseResultAnn: ResponseResult? = request.getAttribute(RESPONSE_RESULT_ANN) as ResponseResult?
        return responseResultAnn != null
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return when(body) {
            is Result<*> -> body
            else -> body
        }
    }
}