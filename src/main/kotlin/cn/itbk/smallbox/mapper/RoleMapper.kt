package cn.itbk.smallbox.mapper

import cn.itbk.smallbox.domain.Role
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【role(角色标识)】的数据库操作Mapper
 * @createDate 2022-03-06 20:20:36
 * @Entity cn.itbk.smallbox.domain.Role
 */
@Mapper
interface RoleMapper : BaseMapper<Role?> {

    fun queryUserRoleId(userid: String): List<String>

}