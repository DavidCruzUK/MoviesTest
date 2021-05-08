package uk.co.davidcruz.moviestest.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.davidcruz.service.ServiceApi
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://movies-sample.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideServiceApiResponse(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)

}