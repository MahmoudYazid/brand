package com.yazid.brand.Repository.onlineResourse

import android.util.Log
import com.yazid.brand.model.ResponseItem
import retrofit2.Response
import javax.inject.Inject

class apiServiceImplement()  {


    suspend fun GetAllProductData(): List<ResponseItem> {
        val data = ProductsApi.retrofitService.getproducts()
        return data

    }

    suspend fun GetAllCatigories(): List<String> {
        val data = ProductsApi.retrofitService.getCatigories()
        return data

    }
    suspend fun GetAllProductDataWithLimit(LimitInput:Int): List<ResponseItem> {
        val data = ProductsApi.retrofitService.getproductsWithLimit(LimitInput)
        return data

    }
    suspend fun GetSpecificCatigory(Category:String): List<ResponseItem> {
        val data = ProductsApi.retrofitService.getSpecificCategories(Category)
        return data

    }


    suspend fun GetSpecificId(Id:String): ResponseItem {
        val data = ProductsApi.retrofitService.getSpecificId(Id)
        return data

    }
}