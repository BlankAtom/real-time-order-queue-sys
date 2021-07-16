package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.dish")
class Dish : Serializable {
    var d_id: String? = null
    var d_name: String? = null
    var m_id: Int? = null
    var d_price: Double? = null
    var d_pic: String? = null
    var d_note: String? = null
    var d_sum: Int? = null
    var cuision_code: String? = null
    var created_at: LocalDateTime? = null
    var updated_at: LocalDateTime? = null

    override fun toString(): String {
        return "Dish{" +
                "d_id=" + d_id +
                ", d_name=" + d_name +
                ", m_id=" + m_id +
                ", d_price=" + d_price +
                ", d_pic=" + d_pic +
                ", d_note=" + d_note +
                ", d_sum=" + d_sum +
                ", cuision_code=" + cuision_code +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}"
    }
}