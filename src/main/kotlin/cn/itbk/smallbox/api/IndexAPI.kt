package cn.itbk.smallbox.api

import cn.itbk.smallbox.api.vo.IndexVo
import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.mapper.IndexMapper
import cn.itbk.smallbox.service.impl.CarouselServiceImpl
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * 拓展商城首页
 *@author Hor
 *@create 2022/3/8 14:23
 */

@RestController
@ResponseResult
@RequestMapping("/api")
class IndexAPI {

    @Resource
    lateinit var carouselServiceImpl: CarouselServiceImpl

    @Resource
    lateinit var indexMapper: IndexMapper

    // 首页
    @GetMapping("/index")
    fun index(): Result<Any> {
        return ResultGenerator.success(
            data = IndexVo(
                carousels = carouselServiceImpl.list(),
                goods = indexMapper.selectHomeApplet()
            )
        )
    }

}