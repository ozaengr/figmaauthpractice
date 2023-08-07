package com.desire.figmaauthpractice.home

import com.google.gson.annotations.SerializedName


data class RcvModel(

    @SerializedName("category")
    var category: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("price")
    var price: Int = 0,
    @SerializedName("title")
    var title: String = ""
    
)
