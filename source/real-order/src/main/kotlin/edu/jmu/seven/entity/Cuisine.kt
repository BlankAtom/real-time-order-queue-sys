package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 *
 *
 *
 *
 *
 * @author Seven
 * @since 2021-07-16
 */
@TableName("vocation.cuisine")
class Cuisine : Serializable {
    var code: Int? = null
    var name: String? = null

    override fun toString(): String {
        return "Cuisine{" +
                "code=" + code +
                ", name=" + name +
                "}"
    }
}