package edu.jmu.seven.entity

import java.io.Serializable

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/19/10:54
 */
class AccountTokenEntity(
    var token: String,
    var username: String,
    var role: String
) : Serializable {
}