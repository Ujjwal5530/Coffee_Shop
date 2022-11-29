package com.malhotra.coffeeshop.modelclass

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class ProfileInfo(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    @ColumnInfo(name = "name")
    var name : String?,
    @ColumnInfo(name = "phone")
    var phone : String?,
    @ColumnInfo(name = "email")
    var email : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfileInfo> {
        override fun createFromParcel(parcel: Parcel): ProfileInfo {
            return ProfileInfo(parcel)
        }

        override fun newArray(size: Int): Array<ProfileInfo?> {
            return arrayOfNulls(size)
        }
    }
}
