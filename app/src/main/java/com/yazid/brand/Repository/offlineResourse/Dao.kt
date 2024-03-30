package com.yazid.brand.Repository.offlineResourse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yazid.brand.model.DBClassItem

@Dao
interface DbDao {
    @Query("SELECT * FROM DBClassItem")
    suspend fun getAll(): List<DBClassItem>




    @Insert
    suspend fun insert(item: DBClassItem)

    @Delete
    suspend fun delete(item: DBClassItem)


}