package com.yazid.brand.Repository.offlineResourse

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yazid.brand.model.DBClassItem
import javax.inject.Inject

class RoomImplementationClass @Inject constructor(val Dao: AppDatabase) {
    suspend fun getAll(): List<DBClassItem>{
        return  Dao.ControlerDao().getAll()
    }




    suspend fun insert(item: DBClassItem){

        return Dao.ControlerDao().insert(item)
    }

    suspend fun delete(item: DBClassItem){
        return Dao.ControlerDao().delete(item)
    }





}