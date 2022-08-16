package cn.itbk.smallbox.service

/**
 *
 *@author Hor
 *@create 2022/4/15 10:17
 */
interface IEmailService {

    /**
     * 发送邮件
     * @param email
     * @return Boolean
     */
    fun sendEmailCode(email: String) : Boolean

}