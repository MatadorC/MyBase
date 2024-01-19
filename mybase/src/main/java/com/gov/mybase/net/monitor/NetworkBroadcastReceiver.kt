package com.gov.mybase.net.monitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.text.TextUtils
import com.gov.mybase.utils.LogUtils

/**
 * ===================================
 * @PackageName ：com.gov.mybase.net.monitor
 * @Description ：网络状态的监听广播
 * @Author ：fengxiaochuan
 * @Date ：2023/11/14 12:07
 * @Version ：1.0
 * ===================================
 */
class NetworkBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "NetworkBroadcastReceiver"
    private var mBroadcastCallback: NetworkBroadcastCallback? = null
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == null) {
            LogUtils.e("$TAG：onReceive#intent=$intent")
            return
        }
        val action = intent.action
        LogUtils.d("$TAG：onReceive#action=$action")
        if (TextUtils.equals(intent.action, ConnectivityManager.CONNECTIVITY_ACTION)) {
            // 申请权限；
//        if (!XXPermissions.isGrantedPermission(context, Permission.WRITE_EXTacERNAL_STORAGE,
//                Permission.READ_EXTERNAL_STORAGE)) {
//        }
//        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
//            //WIFI和移动网络均未连接
//            netContentListener.netContent(false);
//        } else {
//            //WIFI连接或者移动网络连接
//            netContentListener.netContent(true);
//        }
            val isOnline = NetworkUtils.isOnline(context)
            val networkStatus = NetworkUtils.getNetWorkState(context)
            LogUtils.d("$TAG：onReceive#isOnline=$isOnline, networdStatus=$networkStatus")
            if (mBroadcastCallback != null) {
                mBroadcastCallback!!.onNetworkBroadcastCallback(isOnline, networkStatus)
            }
        }
    }

    fun setBroadcastCallback(broadcastCallback: NetworkBroadcastCallback?) {
        mBroadcastCallback = broadcastCallback
    }

    interface NetworkBroadcastCallback {
        /**
         * 根据监听的结果返回连接状态和网络状态；
         *
         * @param isConnected
         * @param networkStatus
         */
        fun onNetworkBroadcastCallback(
            isConnected: Boolean,
            networkStatus: NetworkStatus?
        )
    }
}