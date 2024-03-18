package com.yazid.brand.Repository.onlineResourse


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


object ProductsApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://fakestoreapi.com")
        .build()

    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}