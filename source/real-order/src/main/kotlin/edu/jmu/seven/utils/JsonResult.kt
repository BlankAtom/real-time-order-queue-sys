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
    var errorCode: Int? = null
    var errorMsg: String? = null
    var data: T? = null
        private set

    constructor() {}
    constructor(success: Boolean) {
        this.success = success
        errorCode = if (success) ResultCode.SUCCESS.code else ResultCode.COMMON_FAIL.code
        errorMsg = if (success) ResultCode.SUCCESS.message else ResultCode.COMMON_FAIL.message
    }

    constructor(success: Boolean, resultEnum: ResultCode?) {
        this.success = success
        errorCode = if (success) ResultCode.SUCCESS.code else resultEnum?.code ?: ResultCode.COMMON_FAIL.code
        errorMsg = if (success) ResultCode.SUCCESS.message else resultEnum?.message ?: ResultCode.COMMON_FAIL.message
    }

    constructor(success: Boolean, data: T) {
        this.success = success
        errorCode = if (success) ResultCode.SUCCESS.code else ResultCode.COMMON_FAIL.code
        errorMsg = if (success) ResultCode.SUCCESS.message else ResultCode.COMMON_FAIL.message
        this.data = data
    }

    constructor(success: Boolean, resultEnum: ResultCode?, data: T) {
        this.success = success
        errorCode = if (success) ResultCode.SUCCESS.code else resultEnum?.code ?: ResultCode.COMMON_FAIL.code
        errorMsg = if (success) ResultCode.SUCCESS.message else resultEnum?.message ?: ResultCode.COMMON_FAIL.message
        this.data = data
    }

    fun setData(data: T) {
        this.data = data
    }
}
