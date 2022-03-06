package cn.itbk.smallbox.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import cn.itbk.smallbox.domain.Carousel
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【carousel(轮播图)】的数据库操作Mapper
 * @createDate 2022-03-08 14:42:58
 * @Entity cn.itbk.smallbox.domain.Carousel
 */
@Mapper
interface CarouselMapper : BaseMapper<Carousel?>