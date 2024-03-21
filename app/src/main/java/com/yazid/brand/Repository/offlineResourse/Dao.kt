package com.yazid.brand.Repository.offlineResourse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yazid.brand.model.DBClassItem

@Dao
interface DbDao {
    @Query("SELECT * FROM DBClassItem")
    fun getAll(): List<DBClassItem>

//    @Query("SELECT * FROM DBClassItem")
//    fun IncreaseItemNo(id: Int)

//    @Query("SELECT * FROM DBClassItem")
//    fun DecreaseItemNo(id: Int)

    @Insert
    fun insert(item: DBClassItem)

    @Delete
    fun delete(item: DBClassItem)
}