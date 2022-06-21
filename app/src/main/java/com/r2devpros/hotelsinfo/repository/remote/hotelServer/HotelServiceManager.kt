package com.r2devpros.hotelsinfo.repository.remote.hotelServer

import com.r2devpros.hotelsinfo.repository.remote.toPretty

class HotelServiceManager(
    private val hotelService: HotelService
) {
    suspend fun search(query: String) = kotlin.runCatching {
        hotelService.search(query)
    }.toPretty()
}