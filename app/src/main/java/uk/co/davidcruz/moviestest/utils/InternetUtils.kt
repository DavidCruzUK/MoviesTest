package uk.co.davidcruz.moviestest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object InternetUtils {
    @Suppress("deprecation")
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkInternetConnectionForSdk23OrAbove(connectivityManager)
        } else {
            connectivityManager?.run {
                connectivityManager.activeNetworkInfo?.run {
                    return type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE
                }
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetConnectionForSdk23OrAbove(connectivityManager: ConnectivityManager?): Boolean {
        return connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }
}