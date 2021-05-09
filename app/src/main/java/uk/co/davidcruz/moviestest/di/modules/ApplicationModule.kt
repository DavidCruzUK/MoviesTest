package uk.co.davidcruz.moviestest.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import uk.co.davidcruz.moviestest.utils.InternetUtils.isInternetAvailable
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

}