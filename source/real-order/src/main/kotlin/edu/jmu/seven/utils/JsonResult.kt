package edu.jmu.seven.utils

import edu.jmu.seven.config.ResultCode

import java.io.Serializable


/**
 * @Author: Hutengfei
 * @Description: 统一返回实体
 * @Date Create in 2019/7/22 19:20
 */
class JsonResult<T> : Serializable {
    var success: Boolean? = null
    var code: Int? = null
    var msg: String? = null
    var data: T? = null
        private set

    constructor() {}
    constructor(success: Boolean) {
        this.success = success
        code = if (success) ResultCode.SUCCESS.code else ResultCode.COMMON_FAIL.code
        msg = if (success) ResultCode.SUCCESS.message else ResultCode.COMMON_FAIL.message
    }

    constructor(success: Boolean, resultEnum: ResultCode?) {
        this.success = success
        code = if (success) ResultCode.SUCCESS.code else resultEnum?.code ?: ResultCode.COMMON_FAIL.code
        msg = if (success) ResultCode.SUCCESS.message else resultEnum?.message ?: ResultCode.COMMON_FAIL.message
    }

    constructor(success: Boolean, data: T) {
        this.success = success
        code = if (success) ResultCode.SUCCESS.code else ResultCode.COMMON_FAIL.code
        msg = if (success) ResultCode.SUCCESS.message else ResultCode.COMMON_FAIL.message
        this.data = data
    }

    constructor(success: Boolean, resultEnum: ResultCode?, data: T) {
        this.success = success
        code = if (success) ResultCode.SUCCESS.code else resultEnum?.code ?: ResultCode.COMMON_FAIL.code
        msg = if (success) ResultCode.SUCCESS.message else resultEnum?.message ?: ResultCode.COMMON_FAIL.message
        this.data = data
    }

    fun setData(data: T) {
        this.data = data
    }
}
