package cn.itbk.smallbox.util

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 *
 *@author Hor
 *@create 2022/3/2 21:40
 */
object HttpContextUtil {

    fun getHttpServletRequest(): HttpServletRequest {
        return (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request
    }

}
