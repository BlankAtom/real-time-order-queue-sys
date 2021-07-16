package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 *
 *
 *
 *
 *
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.account")
class Account : Serializable {
    var username: String? = null
    var password: String? = null
    var auth_id: Int? = null
    var created_at: LocalDateTime? = null
    var updated_at: LocalDateTime? = null

    override fun toString(): String {
        return "Account{" +
                "username=" + username +
                ", password=" + password +
                ", auth_id=" + auth_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}"
    }
}