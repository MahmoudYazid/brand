package com.yazid.brand.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName




@Entity(tableName = "DBClassItem")
data class DBClassItem(
    @ColumnInfo(name = "image")

    val image: String ,
    @ColumnInfo(name = "price")

    val price: Float ,

    @ColumnInfo(name = "description")

    val description: String ,

    @PrimaryKey()
    val id: Int? ,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "rate")

    val rate: Float ,
    @ColumnInfo(name = "count")

    val count: Int
)
