package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.customer")
class Customer(
    @TableId("c_id")
    var c_id: String,

    @TableField("c_name")
    var c_name: String,

    @TableField("c_phone")
    var c_phone: String
) : Serializable {


    /**
     * createTime: 字段，创建时间，记录创建记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "created_at" ,fill = FieldFill.INSERT)
    var createTime: LocalDateTime = LocalDateTime.now()

    /**
     * updateTime: 字段，更新时间，记录更新记录的最后时间
     * 不需要开发者进行设定，由MetaHandler进行
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime = LocalDateTime.now()


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
        return "Customer{" +
                "c_id=" + c_id +
                ", c_name=" + c_name +
                ", c_phone=" + c_phone +
                ", created_at=" + createTime +
                ", updated_at=" + updateTime +
                "}"
    }
}