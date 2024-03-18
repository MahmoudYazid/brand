package com.yazid.brand.dependancyInjection

import com.yazid.brand.Repository.onlineResourse.ApiService
import com.yazid.brand.Repository.onlineResourse.apiServiceImplement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module {




    @Provides
    @Singleton
    fun ApiFunBuild(): apiServiceImplement{

        return apiServiceImplement()
    }

}