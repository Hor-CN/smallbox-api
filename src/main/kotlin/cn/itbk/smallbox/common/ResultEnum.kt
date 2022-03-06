package cn.itbk.smallbox.common

/**
 *
 *@author Hor
 *@create 2022/3/2 20:53
 */
enum class ResultEnum(val message: String) {
    SUCCESS("成功"),
    FAIL("失败"),
    CREATE_SUCCESS("创建成功"),
    DELETE_SUCCESS("删除成功"),
    NOT_FOUND("找不到对应的资源"),
    UNKNOWN_ERROR("未知错误"),
    LOGIN_SUCCESS("登录成功"),
    LOGIN_LOGOUT("注销登录成功"),
    LOGIN_ERROR("账号或密码错误，登录失败"),
    USER_SUCCESS("注册成功"),
    USER_FAIL("注册失败"),
    USER_NO_EXISTS("用户不存在"),
    USER_ALREADY_EXISTS("用户已存在"),
    USER_UPDATE_SUCCESS("修改成功"),
    USER_UPDATE_FAIL("修改失败")

}