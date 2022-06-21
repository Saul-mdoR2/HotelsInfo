package com.r2devpros.hotelsinfo.repository.remote.hotelServer

import com.r2devpros.hotelsinfo.model.SuggestionModel

interface HotelService {
    suspend fun search(query: String): List<SuggestionModel>
}