package cn.itbk.smallbox.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import cn.itbk.smallbox.domain.UserLocalAuth
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【user_local_auth】的数据库操作Mapper
 * @createDate 2022-04-14 20:05:24
 * @Entity cn.itbk.smallbox.domain.UserLocalAuth
 */
@Mapper
interface UserLocalAuthMapper : BaseMapper<UserLocalAuth?>