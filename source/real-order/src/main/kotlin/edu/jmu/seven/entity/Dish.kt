package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.dish")
class Dish (
    @TableId("d_id")
    var d_id: String,

    @TableField(value = "d_name")
    var d_name: String,

    @TableField(value = "m_id")
    var m_id: String,

    @TableField(value = "d_price")
    var d_price: Double,

    @TableField(value = "d_pic")
    var d_pic: String,
    @TableField(value = "d_note")
    var d_note: String,
    @TableField(value = "d_sum")
    var d_sum: Int,

    @TableField(value = "cuision_code")
    var cuision_code: String,

        ) : Serializable {




    @TableField(value = "created_at" ,fill = FieldFill.INSERT)
    var createTime: LocalDateTime = LocalDateTime.now()

    /**
     * updateTime: 字段，更新时间，记录更新记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime = LocalDateTime.now()

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
                ", created_at=" + createTime +
                ", updated_at=" + updateTime +
                "}"
    }
}