package cn.itbk.smallbox.service.impl

import cn.itbk.smallbox.domain.Category
import cn.itbk.smallbox.mapper.CategoryMapper
import cn.itbk.smallbox.service.CategoryService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * @author Hor
 * @description 针对表【category(父类表)】的数据库操作Service实现
 * @createDate 2022-03-07 19:45:52
 */
@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper?, Category?>(), CategoryService