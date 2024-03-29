package com.gov.mybase.net

import cxhttp.CxHttpHelper
import cxhttp.call.Okhttp3Call
import kotlinx.coroutines.MainScope
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


/**
 * ===================================
 * @PackageName ：com.gov.mybase.net
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/6/29 18:05
 * @Version ：1.0
 * ===================================
 */
class CxHttpUtils {
    companion object {
        fun init() {
            CxHttpHelper.init(scope = MainScope(), debugLog = true, call = Okhttp3Call {
                callTimeout(300, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            })
            CxHttpHelper.hookRequest { request ->
                request
            }
            CxHttpHelper.hookResponse { response ->
                //此处可以预处理请求结果，例如token失效自动刷新并重试功能、制作假数据测试等等
                response.request
                response
            }
        }
    }
}