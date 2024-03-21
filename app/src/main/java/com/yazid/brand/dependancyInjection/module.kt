package com.yazid.brand.dependancyInjection

import android.content.Context
import androidx.room.Room
import com.yazid.brand.Repository.offlineResourse.AppDatabase
import com.yazid.brand.Repository.offlineResourse.RoomImplementationClass
import com.yazid.brand.Repository.onlineResourse.apiServiceImplement
import com.yazid.brand.viewModel.viewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideMyViewModel(roomInst:RoomImplementationClass ,Api:apiServiceImplement, @ApplicationContext appContext: Context): viewModel {
        return viewModel(Api,appContext,roomInst)
    }
    @Provides
    @Singleton
    fun provideBuildDbInstance( @ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "productsDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provide_DatabaseRepo_Inst(Dao:AppDatabase):RoomImplementationClass{
        return  RoomImplementationClass(Dao)
    }

}