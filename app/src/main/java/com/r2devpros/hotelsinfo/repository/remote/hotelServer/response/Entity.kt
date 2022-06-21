package com.r2devpros.hotelsinfo.repository.remote.hotelServer.response


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Entity(
    @Json(name = "caption")
    val caption: String?,
    @Json(name = "destinationId")
    val destinationId: String?,
    @Json(name = "geoId")
    val geoId: String?,
    @Json(name = "landmarkCityDestinationId")
    val landmarkCityDestinationId: Any?,
    @Json(name = "latitude")
    val latitude: Double?,
    @Json(name = "longitude")
    val longitude: Double?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "redirectPage")
    val redirectPage: String?,
    @Json(name = "searchDetail")
    val searchDetail: Any?,
    @Json(name = "type")
    val type: String?
)