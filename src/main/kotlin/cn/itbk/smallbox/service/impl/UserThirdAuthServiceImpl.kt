package cn.itbk.smallbox.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import cn.itbk.smallbox.mapper.UserThirdAuthMapper
import cn.itbk.smallbox.domain.UserThirdAuth
import cn.itbk.smallbox.service.UserThirdAuthService
import org.springframework.stereotype.Service

/**
 * @author Hor
 * @description 针对表【user_third_auth】的数据库操作Service实现
 * @createDate 2022-04-20 20:47:41
 */
@Service
class UserThirdAuthServiceImpl : ServiceImpl<UserThirdAuthMapper, UserThirdAuth>(), UserThirdAuthService