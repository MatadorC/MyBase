package com.base.mybase

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.gov.mybase.net.MyCxHttpCall
import com.gov.mybase.utils.LogUtils
import com.gov.mybase.utils.TimeUtils
import cxhttp.CxHttp
import cxhttp.CxHttpHelper
import cxhttp.call.Okhttp3Call
import cxhttp.converter.JacksonConverter
import cxhttp.response.body
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv = findViewById<TextView>(R.id.tv)
        tv.text=TimeUtils.getSupportBeginDayofMonth("2022-11-9")
    }
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)


//        val okhttp3Call = Okhttp3Call {
//            callTimeout(15, TimeUnit.SECONDS)
//            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        }
//        val jacksonConverter = JacksonConverter()
//        CxHttpHelper.init(scope=MainScope(), debugLog=true, call=okhttp3Call, converter=jacksonConverter)

//        val job = CxHttp.get("https://www.baidu.com")
//            //此处可指定协程，不指定默认使用CxHttpHelper.scope
//            .launch { response ->
//                if (response.body != null) {
//                    println("resultGet1: ${response.body}")
//                } else {
//                    // TODO: Can do some exception handling
//                }
//            }
//    }
}