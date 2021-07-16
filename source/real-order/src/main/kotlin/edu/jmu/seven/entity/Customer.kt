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
@TableName("vocation.customer")
class Customer : Serializable {
    var c_id: String? = null
    var c_name: String? = null
    var c_phone: String? = null
    var created_at: LocalDateTime? = null
    var updated_at: LocalDateTime? = null


    override fun toString(): String {
        return "Customer{" +
                "c_id=" + c_id +
                ", c_name=" + c_name +
                ", c_phone=" + c_phone +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}"
    }
}