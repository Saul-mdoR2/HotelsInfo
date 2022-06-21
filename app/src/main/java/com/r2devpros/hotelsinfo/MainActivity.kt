package com.r2devpros.hotelsinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelRetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hotels4.p.rapidapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(HotelRetrofitService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.search(
                apiKey = "a61a1d1143msha75bba8b74f07d4p13cbd1jsn580989349a45",
                apiHost = "hotels4.p.rapidapi.com",
                query = "new york",
                locale = "en_US",
                currency = "USD"
            )

            Timber.d("MainActivity_TAG: onCreate: response: $response")
        }
    }
}