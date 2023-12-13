package com.base.mybase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gov.mybase.utils.LogUtils
import com.gov.mybase.utils.TimeUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtils.e(TimeUtils.getSupportBeginDayofMonth("2022-11-9"))

    }
}