package edu.jmu.seven.utils

import edu.jmu.seven.config.ResultCode


/**
 * @Author: Hutengfei
 * @Description:
 * @Date Create in 2019/7/22 19:52
 */
object ResultTool {
    fun success(): JsonResult<*> {
        return JsonResult<Any?>(true)
    }

    fun <T> success(data: T): Any {
        return JsonResult<Any?>(true, data)
    }

    fun fail(): JsonResult<*> {
        return JsonResult<Any?>(false)
    }

    fun fail(resultEnum: ResultCode?): JsonResult<*> {
        return JsonResult<Any?>(false, resultEnum)
    }
}
