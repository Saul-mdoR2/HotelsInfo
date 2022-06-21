package com.r2devpros.hotelsinfo.repository.remote.hotelServer

import com.r2devpros.hotelsinfo.model.EntityModel
import com.r2devpros.hotelsinfo.model.SuggestionModel
import com.r2devpros.hotelsinfo.repository.remote.decodeResponse
import timber.log.Timber

class HotelServiceImpl(
    private val hotelRetrofitService: HotelRetrofitService
) : HotelService {
    override suspend fun search(query: String): List<SuggestionModel> {
        Timber.d("HotelServiceImpl_TAG: search: $query")
        return hotelRetrofitService.search(
            apiKey = "a61a1d1143msha75bba8b74f07d4p13cbd1jsn580989349a45",
            apiHost = "hotels4.p.rapidapi.com",
            query = query,
            locale = "en_US",
            currency = "USD"
        ).decodeResponse { searchResponse ->
            searchResponse.suggestions?.map { suggestion ->
                SuggestionModel(
                    groupType = suggestion.group ?: "",
                    entities = suggestion.entities?.map { entity ->
                        EntityModel(
                            geoId = entity.geoId ?: "",
                            destinationId = entity.destinationId ?: "",
                            type = entity.type ?: "",
                            caption = entity.caption ?: "",
                            name = entity.name ?: ""
                        )
                    } ?: emptyList()
                )
            } ?: emptyList()
        }
    }
}