package com.r2devpros.hotelsinfo.repository.remote.hotelServer.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Suggestion(
    @Json(name = "entities")
    val entities: List<Entity>?,
    @Json(name = "group")
    val group: String?
)