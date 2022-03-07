package cn.itbk.smallbox.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import cn.itbk.smallbox.domain.Applet
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【applet(拓展插件表)】的数据库操作Mapper
 * @createDate 2022-03-07 14:43:19
 * @Entity cn.itbk.smallbox.domain.Applet
 */
@Mapper
interface AppletMapper : BaseMapper<Applet> {

//    fun selectKeyWordPage()

}