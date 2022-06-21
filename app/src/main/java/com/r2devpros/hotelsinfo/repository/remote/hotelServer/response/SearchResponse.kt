package com.r2devpros.hotelsinfo.repository.remote.hotelServer.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "autoSuggestInstance")
    val autoSuggestInstance: Any?,
    @Json(name = "geocodeFallback")
    val geocodeFallback: Boolean?,
    @Json(name = "misspellingfallback")
    val misspellingfallback: Boolean?,
    @Json(name = "moresuggestions")
    val moresuggestions: Int?,
    @Json(name = "suggestions")
    val suggestions: List<Suggestion>?,
    @Json(name = "term")
    val term: String?,
    @Json(name = "trackingID")
    val trackingID: String?
)