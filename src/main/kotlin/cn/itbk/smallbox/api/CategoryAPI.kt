package cn.itbk.smallbox.api

import cn.itbk.smallbox.config.annotation.ResponseResult
import cn.itbk.smallbox.domain.Category
import cn.itbk.smallbox.service.impl.CategoryServiceImpl
import cn.itbk.smallbox.util.Result
import cn.itbk.smallbox.util.ResultGenerator
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * 拓展分类
 *@author Hor
 *@create 2022/3/7 20:11
 */
@RestController
@ResponseResult
@RequestMapping("/api")
class CategoryAPI {

    @Resource
    lateinit var categoryServiceImpl: CategoryServiceImpl

    @GetMapping("/categories")
    fun categories(): Result<Any> {
        return ResultGenerator.success(
            categoryServiceImpl.list(
                QueryWrapper<Category?>()
                    .eq("type", 0)
                    .eq("visible", 1)
            )
        )
    }

}