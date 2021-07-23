package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/13:20
 */


@TableName("vocation.order_dish")
class OrderDish(
    @TableId("od_id")
    var od_id: String,
    @TableField("o_id")
    var o_id: String,
    @TableField("d_id")
    var d_id: String,
    @TableField("num")
    var num: Int
) : Serializable {

    @TableField("create_time", fill=FieldFill.UPDATE)
    var createTime: LocalDateTime = LocalDateTime.now()

    @TableField("update_time", fill=FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime = LocalDateTime.now()


    @Version
    var version: Int = 1


    @TableLogic
    var deleted: Int = 0


    override fun toString(): String {
        return "Oder_dish{" +
                "od_id=" + od_id +
                ", o_id=" + o_id +
                ", d_id=" + d_id +
                ", num=" + num +
                "}"
    }
}