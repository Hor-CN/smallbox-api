package cn.itbk.smallbox.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import cn.itbk.smallbox.domain.UserThirdAuth
import org.apache.ibatis.annotations.Mapper

/**
 * @author Hor
 * @description 针对表【user_third_auth】的数据库操作Mapper
 * @createDate 2022-04-20 20:47:41
 * @Entity cn.itbk.smallbox.domain.UserThirdAuth
 */
@Mapper
interface UserThirdAuthMapper : BaseMapper<UserThirdAuth?>