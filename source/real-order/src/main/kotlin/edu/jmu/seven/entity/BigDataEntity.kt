package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.ibatis.type.JdbcType
import org.springframework.context.annotation.Primary
import java.time.LocalDate
import java.util.*

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/08/02/20:14
 */

@TableName("big_data")
class BigDataEntity(
    @TableId("id", type = IdType.AUTO)
    var id: Int?,
    var first: String?,
    var second: String?,
    @TableField(value = "date_time", jdbcType = JdbcType.DATE)
    var dateTime: LocalDate?,
    var status: Int?
) {


}