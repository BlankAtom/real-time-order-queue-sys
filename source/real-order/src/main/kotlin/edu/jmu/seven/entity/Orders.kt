package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.orders")
class Orders(
    @TableId("o_id")
    var o_id: String,
    @TableField(value = "m_id")
    var m_id: String,
    @TableField(value = "c_id")
    var c_id: String,
    @TableField(value = "o_cost")
    var o_cost: Double,
//    @TableField(value = "o_content")
//    var o_content: String,
    @TableField(value = "o_start_time")
    var o_start_time: LocalDateTime,
    @TableField(value = "o_pay_time")
    var o_pay_time: LocalDateTime,
    @TableField(value = "o_pay_type")
    var o_pay_type: Int,
//    @TableField(value = "o_estimate")
//    var o_estimate: String,
    @TableField(value = "status")
    var status: String,
    @TableField(value = "o_desk_num")
    var o_desk_num: Int
) : Serializable {


    /**
     * createTime: 字段，创建时间，记录创建记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "created_at" ,fill = FieldFill.INSERT)
    var created_at: LocalDateTime = LocalDateTime.now()

    /**
     * updateTime: 字段，更新时间，记录更新记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    var updated_at: LocalDateTime = LocalDateTime.now()


    /**
     * deleted: 字段，逻辑删除，表示是否被删除，当为1（删除）的时候不会被搜索到
     * 不需要开发者进行设定，@TableLogic 会自己完成
     */
    @TableLogic
    var deleted: Int = 0

    /**
     * version: 字段，乐观锁，用于插入、更新时进行版本比较
     * 不需要开发者进行设定，@Version 会自己完成
     */
    @Version
    var version: Int = 1



    override fun toString(): String {
        return "Orders{" +
                "o_id=" + o_id +
                ", m_id=" + m_id +
                ", c_id=" + c_id +
                ", o_cost=" + o_cost +
                ", o_start_time=" + o_start_time +
                ", o_pay_time=" + o_pay_time +
                ", o_pay_type=" + o_pay_type +
                ", status=" + status +
                ", o_desk_num=" + o_desk_num +
                ", created_at=" + created_at +
                ", update_at=" + updated_at +
                ", deleted=" + deleted +
                ", version=" + version +
                "}"
    }
}