package com.malhotra.coffeeshop.modelclass

import android.os.Parcel
import android.os.Parcelable

data class MenuItems(
    val title: String?,
    val image: Int,
    val price: Double,
    val description: String? = "Coffee Shop",
    var quantity : Int = 1) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(image)
        parcel.writeDouble(price)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuItems> {
        override fun createFromParcel(parcel: Parcel): MenuItems {
            return MenuItems(parcel)
        }

        override fun newArray(size: Int): Array<MenuItems?> {
            return arrayOfNulls(size)
        }
    }
}
