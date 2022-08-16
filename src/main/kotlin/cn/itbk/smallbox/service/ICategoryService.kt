package cn.itbk.smallbox.service

import cn.itbk.smallbox.domain.Category
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @author Hor
 * @description 针对表【category(父类表)】的数据库操作Service
 * @createDate 2022-03-07 19:45:52
 */
interface ICategoryService : IService<Category?> {

    /**
     * 获取分类列表
     * @return
     */
    fun findCategoriesList(): List<Category>

}