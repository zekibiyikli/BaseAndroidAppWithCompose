package com.base.androidappwithcompose.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.base.androidappwithcompose.enums.NetworkStatus

class NetworkMonitor(context: Context, private val listener: (NetworkStatus) -> Unit) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            listener(NetworkStatus.Available)
        }

        override fun onLost(network: Network) {
            listener(NetworkStatus.Lost)
        }
    }

    fun register() {
        if (isInternetAvailable()) {
            listener(NetworkStatus.Available)
        } else {
            listener(NetworkStatus.Lost)
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    fun isInternetAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}

