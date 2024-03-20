package com.yazid.brand.dependancyInjection

import android.content.Context
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yazid.brand.Repository.onlineResourse.ApiService
import com.yazid.brand.Repository.onlineResourse.apiServiceImplement
import com.yazid.brand.viewModel.viewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    @Provides
    @Singleton
    fun provideMyViewModel(Api:apiServiceImplement, @ApplicationContext appContext: Context): viewModel {
        return viewModel(Api,appContext)
    }
}