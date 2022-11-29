package com.malhotra.coffeeshop.modelclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourites")
data class FavList(
    @PrimaryKey(autoGenerate = false)
    var id : Int?,
    @ColumnInfo(name = "Title")
    var title : String,
    @ColumnInfo(name = "Image")
    var image: Int,
    @ColumnInfo(name = "Price")
    var price: Double,
    @ColumnInfo(name = "Description")
    var des : String
)
