package cn.itbk.smallbox.config.satoken

import cn.dev33.satoken.session.SaSessionCustomUtil
import cn.dev33.satoken.stp.StpInterface
import cn.dev33.satoken.stp.StpUtil
import cn.itbk.smallbox.mapper.PermissionsMapper
import cn.itbk.smallbox.mapper.RoleMapper
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * [账号id -> 角色id -> 权限列表]
 *@author Hor
 *@create 2022/3/6 19:52
 */
@Component
class StpInterfaceImpl : StpInterface {

    @Resource
    private lateinit var roleMapper: RoleMapper
    @Resource
    private lateinit var permissionsMapper: PermissionsMapper

    // [账号id -> 角色id -> 权限列表]
    override fun getPermissionList(loginId: Any, loginType: String): List<String> {
        // 权限码集合
        val permissionList: MutableList<String> = ArrayList()
        // 创建一个迭代器，遍历角色列表
        val iterable: Iterator<*> = getRoleList(loginId, loginType).iterator()
        // 遍历迭代器
        while (iterable.hasNext()) {
            // 获取角色id
            val roleId = iterable.next() as Int
            // 获取角色Session，不存在则新建
            val roleSession = SaSessionCustomUtil.getSessionById("role-$roleId")
            // 获取角色权限并加入集合中
            permissionList.addAll(roleSession.get("Permission_List") {
                return@get permissionsMapper.queryPermsList(roleId)
            })
        }
        // 返回权限码集合
        return permissionList
    }

    override fun getRoleList(loginId: Any, loginType: String): List<String> {
        // 获取当前账号的session，若不存在则新建返回
        val session = StpUtil.getSessionByLoginId(loginId)
        // 获取session中的角色列表，不存在则新建
        return session.get("Role_List") {
            return@get roleMapper.queryUserRoleId(loginId.toString())
        }

    }
}