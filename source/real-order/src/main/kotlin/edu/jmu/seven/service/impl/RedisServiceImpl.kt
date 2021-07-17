package edu.jmu.seven.service.impl

import edu.jmu.seven.service.RedisService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/17/10:57
 */

@Service
class RedisServiceImpl : RedisService {
    companion object {
        val SEVEN_DAY_SEC: Long = 7 * 24 * 60 * 60
    }
    @Resource
    private val redisTemplate: RedisTemplate<String, Any>? = null

    override fun set(key: String, value: Any) {
        val rs = StringRedisSerializer()
        redisTemplate?.keySerializer = rs
        val vo = redisTemplate?.opsForValue()
        vo?.set(key, value.toString(), SEVEN_DAY_SEC)
    }

    override fun delete(key: String): Boolean {
        return redisTemplate?.delete(key) ?: false
    }

    override fun get(key: String): Any? {
        val ops = redisTemplate?.opsForValue()
        return ops?.get(key)
    }
}