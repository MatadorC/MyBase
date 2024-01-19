package com.gov.mybase.net.monitor

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.gov.mybase.net.monitor.NetworkUtils.getNetWorkState
import com.gov.mybase.utils.LogUtils
import java.util.concurrent.CopyOnWriteArrayList

/**
 * ===================================
 * @PackageName ：com.gov.mybase.net.monitor
 * @Description ：网络状态变化的监听类，根据android不同版本的系统，有 [ConnectivityManager.registerNetworkCallback]和注册广播两种实现方式；
 * @Author ：fengxiaochuan
 * @Date ：2023/11/14 11:55
 * @Version ：1.0
 * ===================================
 */
object NetworkListenerHelper {
    private val TAG = "NetworkListenerHelper"
    private var mContext: Context? = null

    @Volatile
    private var mListenerList: CopyOnWriteArrayList<NetworkConnectedListener>? = null

    /**
     * 注册网络状态的监听；
     */
    @SuppressLint("MissingPermission")
    fun registerNetworkListener() {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                val connectivityManager =
                    mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connectivityManager == null) {
                    LogUtils.i("$TAG：registerNetworkListener#return#connectivityManager=$connectivityManager"
                    )
                    return
                }
                connectivityManager.registerDefaultNetworkCallback(MyNetworkCallback())
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val connectivityManager = mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connectivityManager == null) {
                    LogUtils.i("$TAG：registerNetworkListener#return#connectivityManager=$connectivityManager")
                    return
                }
                val builder: NetworkRequest.Builder
                builder = NetworkRequest.Builder()
                builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                val networkRequest = builder.build()
                connectivityManager.registerNetworkCallback(networkRequest, MyNetworkCallback())
            }
            else -> {
                // 通过广播的方式监听网络；
                val mNetworkBroadcastReceiver = NetworkBroadcastReceiver()
                val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                mContext!!.registerReceiver(mNetworkBroadcastReceiver, filter)
                mNetworkBroadcastReceiver.setBroadcastCallback(object :
                    NetworkBroadcastReceiver.NetworkBroadcastCallback {
                    override fun onNetworkBroadcastCallback(
                        isConnected: Boolean,
                        networkStatus: NetworkStatus?
                    ) {
                        //
                        notifyAllListeners(isConnected, networkStatus!!)
                    }
                })
            }
        }
    }

    /**
     * 通知所有接收者；
     *
     * @param isConnected
     * @param networkStatus
     */
    private fun notifyAllListeners(
        isConnected: Boolean,
        networkStatus: NetworkStatus
    ) {
        if (mListenerList?.isNotEmpty() == true) {
//            mListenerList.stream().forEach(networkConnectedListener -> {
//                networkConnectedListener.onNetworkConnected(isConnected, networdStatus);
//            });
            for (listener in mListenerList!!) {
                listener?.onNetworkConnected(isConnected, networkStatus)
            }
        }
    }

    /**
     * 添加回调的监听者；
     */
    @Synchronized
    fun addListener(listener: NetworkConnectedListener?) {
        if (listener == null) {
            return
        }
        if (mListenerList == null) {
            mListenerList = CopyOnWriteArrayList()
        }
        // 防止重复添加；
        if (!mListenerList!!.contains(listener)) {
            mListenerList!!.add(listener)
        }
    }

    /**
     * 移除某个回调实例；
     *
     * @param listener
     */
    @Synchronized
    fun removeListener(listener: NetworkConnectedListener?) {
        if (listener != null) {
            mListenerList!!.remove(listener)
        }
    }

    fun unregisterNetworkCallback() {
        if (mContext == null) {
            return
        }
        val connectivityManager = mContext!!
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager == null) {
            LogUtils.e("$TAG：registerNetworkListener#return#connectivityManager=$connectivityManager"
            )
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(NetworkCallback())
        }
    }

    interface NetworkConnectedListener {
        /**
         * @param isConnected
         * @param networkStatus
         */
        fun onNetworkConnected(
            isConnected: Boolean,
            networkStatus: NetworkStatus?
        )
    }

    @SuppressLint("NewApi")
    private class MyNetworkCallback : NetworkCallback() {
        //当用户与网络连接（或断开连接）（可以是WiFi或蜂窝网络）时，这两个功能均作为默认回调;
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            LogUtils.d("$TAG：onAvailable#network=$network")
            // 需要同步获取一次网络状态；
            val netWorkState = getNetWorkState(mContext!!)
            LogUtils.d("$TAG：onAvailable#netWorkState=$netWorkState")
            //
            notifyAllListeners(true, netWorkState)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            LogUtils.d("$TAG：onLost#network=$network")
            // 需要同步获取一次网络状态；
            val netWorkState = getNetWorkState(mContext!!)
            LogUtils.d("$TAG：onLost#netWorkState=$netWorkState")
            //
            notifyAllListeners(false, netWorkState)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            LogUtils.d("$TAG：onCapabilitiesChanged#network=$network")
            //            LogUtils.d(TAG, "onCapabilitiesChanged#network=" + network + ", networkCapabilities=" + networkCapabilities);
            // 表示能够和互联网通信（这个为true表示能够上网）
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        LogUtils.d("$TAG：onCapabilitiesChanged#网络类型为wifi")
                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        LogUtils.d("$TAG：onCapabilitiesChanged#蜂窝网络")
                    }
                    else -> {
                        LogUtils.d("$TAG：onCapabilitiesChanged#其他网络")
                    }
                }
            }
        }
    }

    fun init(context: Context): NetworkListenerHelper {
        mContext = context
        return this
    }
}