package cn.itbk.smallbox.mapper

import cn.itbk.smallbox.domain.Permissions
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【permissions(权限表)】的数据库操作Mapper
 * @createDate 2022-03-06 21:15:18
 * @Entity cn.itbk.smallbox.domain.Permissions
 */
@Mapper
interface PermissionsMapper : BaseMapper<Permissions?> {

    fun queryPermsList(roleId: Int): List<String>

}