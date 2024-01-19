package com.gov.mybase.net.monitor

/**
 * ===================================
 * @PackageName ：com.gov.mybase.net.monitor
 * @Description ：网络连接状态的枚举
 * @Author ：fengxiaochuan
 * @Date ：2023/11/14 11:57
 * @Version ：1.0
 * ===================================
 */
enum class NetworkStatus(code: Int, msg: String) {
    /**
     * ；
     */
    NONE(-1, "无网络连接"),

    /**
     * 解析数据内容失败
     */
    MOBILE(0, "移动网络连接"),

    /**
     * 网络问题
     */
    WIFI(1, "WIFI连接");

    private val status: Int
    private val desc: String
    fun getStatus(): Int {
        return status
    }

    fun getDesc(): String {
        return desc
    }

    override fun toString(): String {
        return "NetwordStatus{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                "} " + super.toString()
    }

    init {
        status = code
        desc = msg
    }
}