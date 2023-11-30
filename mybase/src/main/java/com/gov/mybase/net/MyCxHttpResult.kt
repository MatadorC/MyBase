package com.gov.mybase.net

/**
 * ===================================
 * @PackageName ：com.gov.mybase.net
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/7/18 17:32
 * @Version ：1.0
 * ===================================
 */
import com.zicheng.net.cxhttp.response.CxHttpResult

data class MyCxHttpResult<T>(val code: Int,
                           val message: String?,
                           val data: T?): CxHttpResult<T>(code.toString(), message.toString(), data)