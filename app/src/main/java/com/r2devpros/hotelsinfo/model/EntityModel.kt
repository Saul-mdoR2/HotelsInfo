package com.r2devpros.hotelsinfo.model

import com.squareup.moshi.Json

data class EntityModel(
    var geoId: String,
    var destinationId: String,
    var type: String,
    var caption: String,
    var name: String,
    var latitude: Double,
    var longitude: Double
)