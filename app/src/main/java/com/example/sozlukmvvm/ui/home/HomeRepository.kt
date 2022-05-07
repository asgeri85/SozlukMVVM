package com.example.sozlukmvvm.ui.home

import com.example.sozlukmvvm.base.BaseRepository
import com.example.sozlukmvvm.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory):BaseRepository() {
    suspend fun getWordData()=safeApiRequest {
        apiFactory.getAllData()
    }

    suspend fun getSearchWord(ing:String)=safeApiRequest {
        apiFactory.getSearchData(ing)
    }
}