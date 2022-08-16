package cn.itbk.smallbox.api

import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.domain.Applet
import cn.itbk.smallbox.service.impl.AppletServiceImpl
import com.baomidou.mybatisplus.core.metadata.IPage
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
class AppletAPI {

    @Resource
    lateinit var appletServiceImpl: AppletServiceImpl

    /**
     * 搜索小程序
     */
    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) keyword: String,
        @RequestParam(defaultValue = "1", required = false) currentPage: Long,
        @RequestParam(defaultValue = "10", required = false) pageSize: Long,
    ): IPage<Applet> {
        return appletServiceImpl.findSearchAppletPage(keyword, currentPage, pageSize)
    }

    /**
     * 获取小程序详情
     */
    @GetMapping("/detail")
    fun detail(@RequestParam id: Long): Applet {
        return appletServiceImpl.findAppletDetail(id)
    }

}