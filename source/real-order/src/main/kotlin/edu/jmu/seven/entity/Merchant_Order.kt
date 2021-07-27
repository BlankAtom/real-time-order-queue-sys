package edu.jmu.seven.entity

import com.baomidou.mybatisplus.annotation.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author Seven
 * @since 2021-07-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Merchant_Order() {

    var m_name: String? = null
    var m_id: String? = null
    var c_id: String? = null
    var status: String? = null
}