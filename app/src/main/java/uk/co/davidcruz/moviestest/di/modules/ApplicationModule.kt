package uk.co.davidcruz.moviestest.di.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import okhttp3.*
import java.io.File
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providesOkHttpClient(context: Context): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "cache_file")
        val cache = Cache(httpCacheDirectory, 20 * 1024 * 1024)
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptors(context))
            .addNetworkInterceptor(interceptors(context))
            .cache(cache)
            .build()
    }

    private fun interceptors(context: Context) = Interceptor { chain ->
        val originalRequest = chain.request()
        val tenMinutes: Int = 60 * 10 // 60 seconds * 10
        val cacheHeaderValue =
            if (isInternetAvailable(context))
                "public, max-age=$tenMinutes, must-revalidate"
            else
                "public, only-if-cached, max-stale=$tenMinutes"
        val request = originalRequest.newBuilder().build()
        val response = chain.proceed(request)
        response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheHeaderValue)
            .build()
    }

    @Suppress("deprecation")
    private fun isInternetAvailable(context: Context): Boolean {
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