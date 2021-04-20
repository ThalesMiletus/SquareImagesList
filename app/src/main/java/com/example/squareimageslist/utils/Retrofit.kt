package com.example.squareimageslist.utils

import android.util.Log
import com.example.squareimageslist.config.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Retrofit {
    @Provides
    @Singleton
    fun getClient(baseUrl: String = AppConfig.API_ENDPOINT): retrofit2.Retrofit =
            retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(
                            OkHttpClient.Builder()
                                    .connectTimeout(AppConfig.IO_TIMEOUT, TimeUnit.SECONDS)
                                    .readTimeout(AppConfig.IO_TIMEOUT, TimeUnit.SECONDS)
                                    .writeTimeout(AppConfig.IO_TIMEOUT, TimeUnit.SECONDS)
                                    .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Singleton
    fun Throwable.printRetrofitError() {
        this.printStackTrace()
        when (this) {
            is IOException -> Log.e(this::class.java.simpleName,
                    "Network Error happened in Retrofit | cause: ${this.cause} | message: ${this.message}")
            is HttpException -> Log.e(this::class.java.simpleName,
                    "HTTP Exception happened in Retrofit | cause: ${this.cause} | message: ${this.message}")
            else -> Log.e(this::class.java.simpleName,
                    "Unknown Error happened in Retrofit | cause: ${this.cause} | message: ${this.message}")
        }
    }
}