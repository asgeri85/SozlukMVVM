package com.example.sozlukmvvm.model


import com.google.gson.annotations.SerializedName

data class WordResponseModel(
    @SerializedName("kelimeler")
    val kelimeler: List<Kelimeler>?,
    @SerializedName("success")
    val success: Int?
)