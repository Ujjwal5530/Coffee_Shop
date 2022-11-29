package com.malhotra.coffeeshop.modelclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderSummary")
data class SummaryList(
    @PrimaryKey(autoGenerate = false)
    var id : Int?,
    @ColumnInfo(name = "orderNo")
    var orderNo : Int,
    @ColumnInfo(name = "Title")
    var title: String?,
    @ColumnInfo(name = "Image")
    var image: Int,
    @ColumnInfo(name = "Price")
    var price: Double,
    @ColumnInfo(name = "Quantity")
    var quantity : Int = 0
)
