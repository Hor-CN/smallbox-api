package cn.itbk.smallbox.util

import cn.itbk.smallbox.common.ResultEnum

/**
 *
 *@author Hor
 *@create 2022/3/5 13:00
 */
object ResultGenerator {

    private const val serialVersionUID = 1L
    private val DEFAULT_SUCCESS_MESSAGE = ResultEnum.SUCCESS.message
    private val DEFAULT_FAIL_MESSAGE = ResultEnum.FAIL.message
    private const val RESULT_CODE_SUCCESS = 200
    private const val RESULT_CODE_FAIL = 400
    private const val RESULT_CODE_SERVER_ERROR = 500

    fun success(): Result<Any> {
        return Result(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE, null)
    }
    fun success(data: Any): Result<Any> {
        return Result(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE,data)
    }
    fun success(message: String): Result<Any> {
        return Result(RESULT_CODE_SUCCESS, message,null)
    }

    fun success(message: String, data: Any): Result<Any> {
        return Result(RESULT_CODE_SUCCESS, message, data)
    }

    fun fail(message: String): Result<Any> {
        return Result(RESULT_CODE_FAIL, message, null)
    }

    fun error(message: Any): Result<Any> {
        return Result(RESULT_CODE_SERVER_ERROR, message.toString(), null)
    }


}