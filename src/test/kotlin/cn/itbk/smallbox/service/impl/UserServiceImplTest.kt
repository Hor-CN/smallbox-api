package cn.itbk.smallbox.service.impl

import cn.itbk.smallbox.domain.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.Resource

/**
 *
 * @author Thaor
 * @create 2022/3/2 19:56
 */
@SpringBootTest
internal class UserServiceImplTest {

    @Autowired
    lateinit var serviceImpl : UserServiceImpl

    @Test
    fun addUser() {
        val user  = User(
            avatar = "http://q1.qlogo.cn/g?b=qq&nk=2786773385&s=100",
            email = "2786773385@qq.com",
            loginName = "Hor",
        )
        serviceImpl.save(user)
    }

}