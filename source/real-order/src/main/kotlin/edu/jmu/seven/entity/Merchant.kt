package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.merchant")
class Merchant (
    @TableId("m_id")
    var m_id: String,
    @TableField("m_name")
    var m_name: String,
    @TableField("m_license")
    var m_license: String,
    @TableField("m_phone")
    var m_phone: String,
    @TableField("m_address")
    var m_address: String,
    @TableField("m_open_time")
    var m_open_time: String,
    @TableField("m_close_time")
    var m_close_time: String,
    @TableField("m_tag")
    var m_tag: String,
    @TableField("m_notice")
    var m_notice: String,
    @TableField("m_icon")
    var m_icon: String,
) : Serializable {

    @TableField("created_at")
    var created_at: LocalDateTime? = null
    @TableField("updated_at")
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