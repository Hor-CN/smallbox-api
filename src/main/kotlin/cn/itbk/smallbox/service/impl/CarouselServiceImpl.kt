package cn.itbk.smallbox.service.impl

import cn.itbk.smallbox.domain.Carousel
import cn.itbk.smallbox.mapper.CarouselMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import cn.itbk.smallbox.service.CarouselService
import org.springframework.stereotype.Service


/**
 * @author Hor
 * @description 针对表【carousel(轮播图)】的数据库操作Service实现
 * @createDate 2022-03-08 14:42:58
 */
@Service
class CarouselServiceImpl : ServiceImpl<CarouselMapper?, Carousel?>(), CarouselService