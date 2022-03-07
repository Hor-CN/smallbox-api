package cn.itbk.smallbox.api

import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.mapper.AppupdateMapper
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 *
 *@author Hor
 *@create 2022/3/6 23:59
 */
@RestController
@ResponseResult
@RequestMapping("/api")
class AppApi {

    @Resource
    lateinit var appUpdateMapper: AppupdateMapper

    @GetMapping("/appUpdate")
    fun appUpdate(): Result<Any> {
        return ResultGenerator.success(data = appUpdateMapper.queryNewApp())
    }

}