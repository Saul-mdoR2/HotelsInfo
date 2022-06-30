package com.r2devpros.hotelsinfo.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.r2devpros.hotelsinfo.R
import com.r2devpros.hotelsinfo.model.SuggestionModel
import com.r2devpros.hotelsinfo.repository.remote.handle
import com.r2devpros.hotelsinfo.repository.remote.hotelServer.HotelServiceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val hotelServiceManager: HotelServiceManager by inject()
    private lateinit var map: GoogleMap
    private val suggestionsList = MutableLiveData<List<SuggestionModel>>()
    private val coordsList = ArrayList<Pair<Double, Double>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val searchView = findViewById<SearchView>(R.id.svLocation)
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query != "") {
                    callWebService(query)
                    findViewById<ProgressBar>(R.id.pbLoading).visibility = View.VISIBLE
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        suggestionsList.observe(this) {
            findViewById<ProgressBar>(R.id.pbLoading).visibility = View.GONE
            createMap()
        }
    }

    private fun callWebService(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Timber.d("MainActivity_TAG: callWebService: ")
            hotelServiceManager.search(query).handle(
                error = {
                    Timber.d("MainActivity_TAG: callWebService: error: $it")
                }, success = {
                    Timber.d("MainActivity_TAG: callWebService: success: ${it.size}")
                    it.forEach { suggestionModel ->
                        if (suggestionModel.groupType == getString(R.string.txt_hotel_type)) {
                            suggestionModel.entities.forEach { entityModel ->
                                val pair = Pair(entityModel.latitude, entityModel.longitude)
                                coordsList.add(pair)
                            }
                        }
                    }
                    suggestionsList.postValue(it)
                }
            )
        }
    }

    private fun createMap() {
        Timber.d("MainActivity_TAG: createMap: ")
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Timber.d("MainActivity_TAG: onMapReady: ")
        map = googleMap
        drawMarker()
    }

    private fun drawMarker() {
        Timber.d("MainActivity_TAG: drawMarker: ")
        var markerNumber = 1
        coordsList.forEach { coords ->
            val mark = LatLng(coords.first, coords.second)
            map.addMarker(
                MarkerOptions().position(mark)
                    .title(getString(R.string.txt_marker_title, markerNumber.toString()))
            )
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(mark, 18f), 4000, null)
            markerNumber += 1
        }
    }
}