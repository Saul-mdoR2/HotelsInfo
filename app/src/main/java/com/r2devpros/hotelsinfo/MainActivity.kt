package com.r2devpros.hotelsinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

        findViewById<SearchView>(R.id.svLocation).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    callWebService(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun callWebService(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Timber.d("MainActivity_TAG: callWebService: ")
            hotelServiceManager.search(query).handle(
                error = {
                    Timber.d("MainActivity_TAG: callWebService: error: $it")
                }, success = {
                    Timber.d("MainActivity_TAG: callWebService: success: ${it.size}")
                }
            )
        }
    }

}