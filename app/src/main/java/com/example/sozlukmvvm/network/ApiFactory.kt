package com.example.sozlukmvvm.network

import com.example.sozlukmvvm.model.WordResponseModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiFactory {

    @GET("tum_kelimeler.php")
    suspend fun getAllData():WordResponseModel

    @POST("kelime_ara.php")
    @FormUrlEncoded
    suspend fun getSearchData(@Field("ingilizce") ing:String):WordResponseModel
}