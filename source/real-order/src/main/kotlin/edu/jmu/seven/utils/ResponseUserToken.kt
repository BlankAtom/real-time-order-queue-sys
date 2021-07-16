package edu.jmu.seven.utils

import edu.jmu.seven.entity.Account

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/17:49
 */


class ResponseUserToken(
    val token: String,
    val userDetail: Account
) {
}