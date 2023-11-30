package com.gov.mybase.utils

/**
 * @PackageName ：com.naolu.mysafeup.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2021/6/17 14:01
 * @Version ：1.0
 */
object ViewUtils {
    private var lastClickTime: Long = 0

    /**
     * 自定义间隔时间
     *
     * @param miss
     * @return
     */
    fun isDoubleTimeClickLone(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime
        if (timeD in 1 until miss) {
            return true
        }
        lastClickTime = time
        return false
    }

    private var lastClickTime1: Long = 0

    /**
     * 自定义间隔时间
     *
     * @param miss
     * @return
     */
    fun isDoubleTimeClickLone1(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime1
        if (timeD in 1 until miss) {
            return true
        }
        lastClickTime1 = time
        return false
    }

    private var lastClickTime2: Long = 0

    fun resetLongTime2() {
        lastClickTime2 = 0
    }

    /**
     * 自定义间隔时间
     *
     * @param miss
     * @return
     */
    fun isDoubleTimeClickLone2(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime2
        if (timeD in 1 until miss) {
            return true
        }
        lastClickTime2 = time
        return false
    }

    private var lastClickTime3: Long = 0

    /**
     * 自定义间隔时间
     *
     * @param miss
     * @return
     */
    fun isDoubleTimeClickLone3(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime3
        if (timeD in 1 until miss) {
            return true
        }
        lastClickTime3 = time
        return false
    }

    private var lastClickTime4: Long = 0

    /**
     * 自定义间隔时间
     *
     * @param miss
     * @return
     */
    fun isDoubleTimeClickLone4(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime4
        if (timeD in 1 until miss) {
            return true
        }
        lastClickTime4 = time
        return false
    }

    private var btnClickTime: Long = 0

    /**
     * 自定义间隔时间
     * 用于按钮
     * @param miss
     * @return
     */
    fun isBtnDoubleTimeClickLone(miss: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - btnClickTime
        if (timeD in 1 until miss) {
            return true
        }
        btnClickTime = time
        return false
    }
}