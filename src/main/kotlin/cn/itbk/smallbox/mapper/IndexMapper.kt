package cn.itbk.smallbox.mapper

import cn.itbk.smallbox.domain.Index
import cn.itbk.smallbox.dto.GoodsDto
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【index(首页)】的数据库操作Mapper
 * @createDate 2022-03-08 15:56:36
 * @Entity cn.itbk.smallbox.domain.Index
 */
@Mapper
interface IndexMapper : BaseMapper<Index> {

    fun selectHomeApplet() : List<GoodsDto>

}