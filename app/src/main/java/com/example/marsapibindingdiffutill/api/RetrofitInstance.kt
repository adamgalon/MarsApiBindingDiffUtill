package com.example.marsapibindingdiffutill.api

import com.example.marsapibindingdiffutill.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {

    companion object {

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(Constants.BASE_URL)
                .build()
        }

        val api: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }
}