package com.r2devpros.hotelsinfo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.r2devpros.hotelsinfo.repository.remote.handle
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelServiceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val hotelServiceManager: HotelServiceManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://hotels4.p.rapidapi.com/locations/v2/search?query=new%20york&locale=en_US&currency=USD")
                .get()
                .addHeader("X-RapidAPI-Key", "a61a1d1143msha75bba8b74f07d4p13cbd1jsn580989349a45")
                .addHeader("X-RapidAPI-Host", "hotels4.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()

            Timber.d("MainActivity_TAG: onCreate: response: ${response.body()?.string()}")
        }*/

        findViewById<Button>(R.id.btnHelloWorld).setOnClickListener {
            callWebService()
        }
    }

    private fun callWebService() {
        CoroutineScope(Dispatchers.IO).launch {
            Timber.d("MainActivity_TAG: callWebService: ")
            hotelServiceManager.search("New York").handle(
                error = {
                    Timber.d("MainActivity_TAG: callWebService: error: $it")
                }, success = {
                    Timber.d("MainActivity_TAG: callWebService: success: ${it.size}")
                }
            )
        }
    }
}