package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.orders")
class Orders : Serializable {
    private var o_id: String? = null
    private var m_id: String? = null
    private var c_id: String? = null
    private var o_cost: Double? = null
    private var o_content: String? = null
    private var o_start_time: LocalDateTime? = null
    private var o_pay_time: LocalDateTime? = null
    private var o_pay_type: Int? = null
    private var o_estimate: String? = null
    var status: String? = null
    private var o_desk_num: Int? = null
    var created_at: LocalDateTime? = null
    var update_at: LocalDateTime? = null
    var deleted: Int? = null
    var version: Int? = null

    fun geto_id(): String? {
        return o_id
    }

    fun seto_id(o_id: String?) {
        this.o_id = o_id
    }

    fun getm_id(): String? {
        return m_id
    }

    fun setm_id(m_id: String?) {
        this.m_id = m_id
    }

    fun getc_id(): String? {
        return c_id
    }

    fun setc_id(c_id: String?) {
        this.c_id = c_id
    }

    fun geto_cost(): Double? {
        return o_cost
    }

    fun seto_cost(o_cost: Double?) {
        this.o_cost = o_cost
    }

    fun geto_content(): String? {
        return o_content
    }

    fun seto_content(o_content: String?) {
        this.o_content = o_content
    }

    fun geto_start_time(): LocalDateTime? {
        return o_start_time
    }

    fun seto_start_time(o_start_time: LocalDateTime?) {
        this.o_start_time = o_start_time
    }

    fun geto_pay_time(): LocalDateTime? {
        return o_pay_time
    }

    fun seto_pay_time(o_pay_time: LocalDateTime?) {
        this.o_pay_time = o_pay_time
    }

    fun geto_pay_type(): Int? {
        return o_pay_type
    }

    fun seto_pay_type(o_pay_type: Int?) {
        this.o_pay_type = o_pay_type
    }

    fun geto_estimate(): String? {
        return o_estimate
    }

    fun seto_estimate(o_estimate: String?) {
        this.o_estimate = o_estimate
    }

    fun geto_desk_num(): Int? {
        return o_desk_num
    }

    fun seto_desk_num(o_desk_num: Int?) {
        this.o_desk_num = o_desk_num
    }

    override fun toString(): String {
        return "Orders{" +
                "o_id=" + o_id +
                ", m_id=" + m_id +
                ", c_id=" + c_id +
                ", o_cost=" + o_cost +
                ", o_content=" + o_content +
                ", o_start_time=" + o_start_time +
                ", o_pay_time=" + o_pay_time +
                ", o_pay_type=" + o_pay_type +
                ", o_estimate=" + o_estimate +
                ", status=" + status +
                ", o_desk_num=" + o_desk_num +
                ", created_at=" + created_at +
                ", update_at=" + update_at +
                ", deleted=" + deleted +
                ", version=" + version +
                "}"
    }
}