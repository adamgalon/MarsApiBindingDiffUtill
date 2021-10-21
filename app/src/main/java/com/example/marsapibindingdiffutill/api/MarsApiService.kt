package com.example.marsapibindingdiffutill.api

import com.example.marsapibindingdiffutill.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {

    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}