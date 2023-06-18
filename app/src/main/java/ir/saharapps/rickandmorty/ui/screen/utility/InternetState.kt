package ir.saharapps.rickandmorty.ui.screen.utility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object InternetState {
    private var isConnected: Boolean = false

    fun initialize(context: Context) {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, intentFilter)
        updateConnectionState(context)
    }

    fun isConnected(): Boolean {
        return isConnected
    }

    private fun updateConnectionState(context: Context) {

        val context = context.applicationContext
        context?.let { context ->
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            isConnected = networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.let {
                updateConnectionState(context)
            }
        }
    }
}