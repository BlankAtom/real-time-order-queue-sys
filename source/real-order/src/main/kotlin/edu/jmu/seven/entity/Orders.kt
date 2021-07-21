package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.orders")
class Orders (
    @TableId("o_id")
    var o_id: String,
    @TableField("")
    var m_id: String,
    @TableField("")
    var c_id: String,
    @TableField("")
    var o_cost: Double,
    @TableField("")
    var o_content: String,
    @TableField("")
    var o_start_time: LocalDateTime,
    @TableField("")
    var o_pay_time: LocalDateTime,
    @TableField("")
    var o_pay_type: Int,
    @TableField("")
    var o_estimate: String,
    @TableField("")
    var status: String,
    @TableField("")
    var o_desk_num: Int
        ): Serializable {

    @TableField("o_start_time", fill = FieldFill.INSERT)
    var starttime: LocalDateTime = LocalDateTime.now()
    @TableField("o_pay_time", fill = FieldFill.INSERT_UPDATE)
    var paytime: LocalDateTime = LocalDateTime.now()
    @TableField(value = "created_at" ,fill = FieldFill.INSERT)
    var createTime: LocalDateTime = LocalDateTime.now()

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime = LocalDateTime.now()

    @TableLogic
    var deleted: Int = 0

    @Version
    var version: Int = 1
    override fun toString(): String {
        return "Orders(o_id='$o_id', m_id='$m_id', c_id='$c_id', o_cost=$o_cost, o_content='$o_content', o_start_time=$o_start_time, o_pay_time=$o_pay_time, o_pay_type=$o_pay_type, o_estimate='$o_estimate', status='$status', o_desk_num=$o_desk_num, starttime=$starttime, paytime=$paytime, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version)"
    }


}