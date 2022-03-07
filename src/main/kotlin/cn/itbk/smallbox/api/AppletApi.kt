package cn.itbk.smallbox.api

import cn.itbk.smallbox.common.ResultEnum
import cn.itbk.smallbox.common.SmallBoxException
import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.service.impl.AppletServiceImpl
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 *
 *@author Hor
 *@create 2022/3/7 14:50
 */
@RestController
@ResponseResult
@RequestMapping("/api/applet")
class AppletApi {

    @Resource
    lateinit var appletServiceImpl: AppletServiceImpl

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) keyword: String,
        @RequestParam(defaultValue = "1", required = false) currentPage: Long,
        @RequestParam(defaultValue = "10", required = false) pageSize: Long,
    ): Result<Any> {
        return ResultGenerator.success(data = appletServiceImpl.getSearchPage(keyword, currentPage, pageSize))
    }

    @GetMapping("/detail")
    fun detail(@RequestParam id: Long): Result<Any> {
        val applet = appletServiceImpl.getById(id) ?: throw SmallBoxException(ResultEnum.NOT_FOUND.message)
        return ResultGenerator.success(data = applet)
    }




}