package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.cuisine")
class Cuisine(

    @TableField("name")
    var name: String
) : Serializable {
    @TableId(type = IdType.AUTO )
    var code: Int? = null
    override fun toString(): String {
        return "Cuisine{" +
                "code=" + code +
                ", name=" + name +
                "}"
    }
}