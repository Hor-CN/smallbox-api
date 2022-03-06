package cn.itbk.smallbox.mapper

import cn.itbk.smallbox.domain.Category
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【category(父类表)】的数据库操作Mapper
 * @createDate 2022-03-07 19:45:52
 * @Entity cn.itbk.smallbox.domain.Category
 */
@Mapper
interface CategoryMapper : BaseMapper<Category?> {

}