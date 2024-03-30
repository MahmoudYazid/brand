package com.yazid.brand.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "DBClassItem")
data class DBClassItem(
    @ColumnInfo(name = "image")

    val image: String,
    @ColumnInfo(name = "price")

    val price: String,

    @ColumnInfo(name = "description")

    val description: String,

    @PrimaryKey()
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "rate")

    val rate: String,
    @ColumnInfo(name = "count")

    val count: String,

    @ColumnInfo(name = "productId")

    val productId: String
)
