package cn.itbk.smallbox.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import cn.itbk.smallbox.domain.AppUpdate
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【appUpdate(应用更新)】的数据库操作Mapper
 * @createDate 2022-03-06 23:54:42
 * @Entity cn.itbk.smallbox.domain.AppUpdate
 */
@Mapper
interface AppUpdateMapper : BaseMapper<AppUpdate> {

    /**
     *
     */
    fun queryNewApp(): AppUpdate

}