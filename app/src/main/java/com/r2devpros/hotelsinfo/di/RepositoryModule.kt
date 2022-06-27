package com.r2devpros.hotelsinfo.di

import com.r2devpros.hotelsinfo.BuildConfig
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelRetrofitService
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelService
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelServiceImpl
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelServiceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://hotels4.p.rapidapi.com/"

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY

inline fun <reified T> createRetrofitWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(T::class.java)

val apiModule = module {
    single { createOkHttpClient() }
}

val hotelModule = module {
    single<HotelRetrofitService> { createRetrofitWebService(get(), BASE_URL) }
    single<HotelService> { HotelServiceImpl(get()) }
    single { HotelServiceManager(get()) }
}
