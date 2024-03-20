package com.yazid.brand.Repository.onlineResourse

import com.yazid.brand.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/products")
    suspend fun getproducts(): List<ResponseItem>
    @GET("/products/categories")
    suspend fun getCatigories(): List<String>

    @GET("/products")
    suspend fun getproductsWithLimit(

        @Query("limit") limit: Int
    ): List<ResponseItem>


    @GET("products/category/{category}")
    suspend fun getSpecificCategories(
        @Path("category") category: String
    ): List<ResponseItem>
}