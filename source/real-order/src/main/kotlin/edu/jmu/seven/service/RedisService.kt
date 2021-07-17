package edu.jmu.seven.service

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/10:55
 */
interface RedisService {
    fun set(key: String, value: Any)

    fun delete(key: String) : Boolean

    fun get(key: String) : Any?
}