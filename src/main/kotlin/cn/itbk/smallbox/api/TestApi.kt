package cn.itbk.smallbox.api

import cn.itbk.smallbox.common.SmallBoxException
import cn.itbk.smallbox.config.annotation.ResponseResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *@author Hor
 *@create 2022/3/2 21:32
 */
@RestController
@ResponseResult
class TestApi {

    @GetMapping("/test")
    fun test(): SmallBoxException {
        throw SmallBoxException("测试异常")
    }

}