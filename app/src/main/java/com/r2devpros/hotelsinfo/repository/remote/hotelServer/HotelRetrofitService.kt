package com.r2devpros.hotelsinfo.repository.remote.hotelServer

import com.r2devpros.hotelsinfo.repository.remote.hotelServer.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HotelRetrofitService {
    @GET("locations/v2/search")
    suspend fun search(
        //"a61a1d1143msha75bba8b74f07d4p13cbd1jsn580989349a45"
        @Header("X-RapidAPI-Key") apiKey: String,
        //"hotels4.p.rapidapi.com"
        @Header("X-RapidAPI-Host") apiHost: String,
        //query=new%20york&locale=en_US&currency=USD"
        @Query("query") query: String,
        @Query("locale") locale: String,
        @Query("currency") currency: String
    ): Response<SearchResponse>
}