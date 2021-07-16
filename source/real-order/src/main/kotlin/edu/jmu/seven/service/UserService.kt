package edu.jmu.seven.service

import edu.jmu.seven.entity.Account
import edu.jmu.seven.utils.ResponseUserToken
import eu.bitwalker.useragentutils.UserAgent
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/16:25
 */
//@Repository
interface UserService {

    /**
     * 注册
     * @param user 用户名
     */
    fun register(user: Account)

    /**
     * @param uname 用户名
     * @param pwd 密码,密文
     */
    fun login(uname: String, pwd: String, userAgent: String) : ResponseUserToken
}