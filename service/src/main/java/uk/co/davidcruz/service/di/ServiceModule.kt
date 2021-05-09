package uk.co.davidcruz.service.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.davidcruz.service.ServiceApi
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://movies-sample.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideServiceApiResponse(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)

}