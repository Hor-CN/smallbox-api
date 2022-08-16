package cn.itbk.smallbox.common

import cn.dev33.satoken.exception.NotLoginException
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

/**
 *
 *@author :Hor
 *@create 2022/3/2 21:18
 */
@RestControllerAdvice
class SmallBoxExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(SmallBoxExceptionHandler::class.java)


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [BindException::class])
    fun bindException(exception: BindException, response: HttpServletResponse): Result<Any> {
        return ResultGenerator.error(ResultEnum.UNKNOWN_ERROR.message)
    }

    // 自定义异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [SmallBoxException::class])
    fun smallBoxException(exception: SmallBoxException, response: HttpServletResponse): Result<Any>? {
        return exception.message?.let { ResultGenerator.fail(it) }
    }

    // 未登录
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [NotLoginException::class])
    fun notLoginException(exception: NotLoginException): Result<Any>? {
        return exception.message?.let { ResultGenerator.fail(it) }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    fun missingServletRequestParameterException(exception: MissingServletRequestParameterException): Result<Any> {
        return ResultGenerator.fail("参数无效")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun httpMessageNotReadableException(exception: HttpMessageNotReadableException): Result<Any> {
        return ResultGenerator.fail("参数无效")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(exception: MethodArgumentNotValidException): Result<Any>? {
        return exception.bindingResult.allErrors[0].defaultMessage?.let { ResultGenerator.fail(it) }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    fun httpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException) : Result<Any>? {
        return ResultGenerator.fail("不支持请求方法:"+exception.method)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpMediaTypeNotSupportedException::class])
    fun httpMediaTypeNotSupportedException(exception: HttpMediaTypeNotSupportedException) : Result<Any>? {
        return exception.message?.let { ResultGenerator.fail(it) }
    }


}