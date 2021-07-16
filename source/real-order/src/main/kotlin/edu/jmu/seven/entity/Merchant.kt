package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.merchant")
class Merchant : Serializable {
    var m_id: String? = null
    var m_name: String? = null
    var m_license: String? = null
    var m_phone: String? = null
    var m_address: String? = null
    var m_open_time: String? = null
    var m_close_time: String? = null
    var m_tag: String? = null
    var m_notice: String? = null
    var m_icon: String? = null
    var created_at: LocalDateTime? = null
    var updated_at: LocalDateTime? = null


    override fun toString(): String {
        return "Merchant{" +
                "m_id=" + m_id +
                ", m_name=" + m_name +
                ", m_license=" + m_license +
                ", m_phone=" + m_phone +
                ", m_address=" + m_address +
                ", m_open_time=" + m_open_time +
                ", m_close_time=" + m_close_time +
                ", m_tag=" + m_tag +
                ", m_notice=" + m_notice +
                ", m_icon=" + m_icon +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                "}"
    }
}