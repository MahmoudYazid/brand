package com.yazid.brand.Repository.onlineResourse

import com.yazid.brand.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getproducts(): List<ResponseItem>
}