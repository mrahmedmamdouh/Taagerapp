package com.example.taagerapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

    @SerializedName("id")
    @Expose
    val productId: String,

    @SerializedName("createdAt")
    @Expose
    val timestamp: Long,

    @SerializedName("name")
    @Expose
    val productName: String,

    @SerializedName("price")
    @Expose
    val productPrice: Int

) : Parcelable