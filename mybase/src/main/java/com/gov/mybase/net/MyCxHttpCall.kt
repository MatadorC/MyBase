package com.gov.mybase.net

import cxhttp.call.CxHttpCall
import cxhttp.call.Okhttp3Call
import cxhttp.request.Request
import cxhttp.response.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * ===================================
 * @PackageName ：com.gov.mybase.net
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/6/29 18:15
 * @Version ：1.0
 * ===================================
 */
class MyCxHttpCall: CxHttpCall {
    private val okhttp3Call = Okhttp3Call{
        callTimeout(300, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    override suspend fun await(request: Request): Response {
        when (request.url) {
//            TEST_URL_USER_UPDATE -> {
//                return Response(200, "", object: Response.Body(){
//                    override fun string(): String {
//                        return JSON_USER_INFO
//                    }
//                })
//            }
//            TEST_URL_USER_PROJECTS -> {
//                return Response(200, "", object: Response.Body(){
//                    override fun string(): String {
//                        return JSON_PROJECTS
//                    }
//                })
//            }
            else -> {
                return okhttp3Call.await(request)
            }
        }
    }
}