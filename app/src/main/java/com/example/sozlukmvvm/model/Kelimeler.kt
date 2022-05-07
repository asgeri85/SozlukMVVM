package com.example.sozlukmvvm.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kelimeler(
    @SerializedName("ingilizce")
    val ingilizce: String?,
    @SerializedName("kelime_id")
    val kelimeİd: String?,
    @SerializedName("turkce")
    val turkce: String?
):Parcelable