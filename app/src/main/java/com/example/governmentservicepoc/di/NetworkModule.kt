package com.example.governmentservicepoc.di

import com.example.governmentservicepoc.data.api.GovernmentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(
    SingletonComponent::class
)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogging() =
        HttpLoggingInterceptor()
            .apply {
                level =
                HttpLoggingInterceptor
                    .Level.BODY
            }

    @Provides
    @Singleton
    fun provideOkHttp(
        logging:
        HttpLoggingInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(
                "http://10.0.2.2:3000/"
            )
            .client(client)
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideGovernmentApi(
        retrofit: Retrofit
    ): GovernmentApi {

        return retrofit.create(
            GovernmentApi::class.java
        )
    }
}