package cn.itbk.smallbox.mapper

import cn.itbk.smallbox.domain.User
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2022-03-02 17:50:31
 * @Entity cn.itbk.smallbox.domain.User
 */
@Mapper
interface UserMapper : BaseMapper<User?> {

}