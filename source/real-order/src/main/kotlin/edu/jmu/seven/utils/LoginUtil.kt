package edu.jmu.seven.utils

import org.springframework.util.DigestUtils
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/29/12:56
 */
class LoginUtil {
    companion object {
        fun generateToken(uname: String):String {
            val token = StringBuilder()

            // 用户名
            token.append(DigestUtils.md5Digest(uname.toByteArray()))
            // 加时间
            val tm = LocalDateTime.now()
            token.append("${tm.year}${tm.monthValue}${tm.dayOfMonth}${tm.hour}${tm.minute}${tm.second}")

            // 三位随机数
            token.append(Random().nextInt(999 - 111 + 1) + 111)

            return token.toString()
        }

        fun generateAuth(au: Int): String  {
            return when(au) {
                1 -> "customer"
                2 -> "merchant"
                else -> "admin"
            }
        }
    }
}