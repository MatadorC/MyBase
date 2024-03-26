package com.base.mybase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.gov.mybase.utils.LogUtils
import com.gov.mybase.utils.TimeUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv = findViewById<TextView>(R.id.tv)
//        GlobalScope.launch {
//            delay(1000)
//            LogUtils.e("eeeeeee"+TimeUtils.getSupportBeginDayofMonth("2022-11-9"))
//
//        }
        tv.text=TimeUtils.getSupportBeginDayofMonth("2022-11-9")

    }
}