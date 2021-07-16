package edu.jmu.seven.exception

import java.io.Serializable
import java.lang.RuntimeException

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/07/16/18:51
 */
class CustomException(
    var code: Int,
    var msg: String
) : RuntimeException() {
    fun instance(c: Int, m: String) : CustomException {
        return CustomException(c, m)
    }
}