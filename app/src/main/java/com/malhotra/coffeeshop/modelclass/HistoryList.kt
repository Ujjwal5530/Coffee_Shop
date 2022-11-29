package com.malhotra.coffeeshop.modelclass

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderHistory")
data class HistoryList(
    @PrimaryKey(autoGenerate = false)
    var id : Int?,
    @ColumnInfo(name = "Total Price")
    var total : Double,
    @ColumnInfo(name = "Items")
    var items : Int,
    @ColumnInfo(name = "Date")
    var date : String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeDouble(total)
        parcel.writeInt(items)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryList> {
        override fun createFromParcel(parcel: Parcel): HistoryList {
            return HistoryList(parcel)
        }

        override fun newArray(size: Int): Array<HistoryList?> {
            return arrayOfNulls(size)
        }
    }
}
