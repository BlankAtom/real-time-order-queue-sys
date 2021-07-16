package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/16:28
 */

@TableName("vocation.permission")
class UserRole(
    val code: Int,
    val name: String
) : Serializable{

}