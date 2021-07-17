package edu.jmu.seven.service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/13:27
 */
interface LoginService {
    fun check(userAgent: String) : Boolean
    fun login(phone: String, pwd: String) : String
    fun logout(token: String) : Boolean
}