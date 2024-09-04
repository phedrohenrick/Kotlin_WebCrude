package com.example.outsideresouces

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("_id")
    val _id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price_in_cents")
    val price_in_cents: Int,

    @SerializedName("active")
    val active: Boolean
)
