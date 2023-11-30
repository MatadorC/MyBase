package com.gov.mybase.utils

import android.text.Editable
import android.widget.EditText

/**
 * ===================================
 * @PackageName ：com.gov.mybase.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/5/31 14:51
 * @Version ：1.0
 * ===================================
 */
object EditTextUtils {
    fun editTextStr(content:String,ed: EditText){
        ed.text = Editable.Factory.getInstance().newEditable(content)
    }
}