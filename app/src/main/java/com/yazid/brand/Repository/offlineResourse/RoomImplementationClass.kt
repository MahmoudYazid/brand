package com.yazid.brand.Repository.offlineResourse

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yazid.brand.model.DBClassItem
import javax.inject.Inject

class RoomImplementationClass @Inject constructor(val Dao: AppDatabase) {
    fun getAll(): List<DBClassItem>{
        return  Dao.ControlerDao().getAll()
    }

//    fun IncreaseItemNo(Id: Int){
//
//        return  Dao.ControlerDao().IncreaseItemNo(Id)
//    }

//    fun DecreaseItemNo(Id: Int){
//        return  Dao.ControlerDao().DecreaseItemNo(Id)
//    }

    fun insert(item: DBClassItem){

        return Dao.ControlerDao().insert(item)
    }

    fun delete(item: DBClassItem){
        return Dao.ControlerDao().delete(item)
    }




}