package com.yazid.brand.Repository.offlineResourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazid.brand.model.DBClassItem

@Database(entities = [DBClassItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ControlerDao(): DbDao
}