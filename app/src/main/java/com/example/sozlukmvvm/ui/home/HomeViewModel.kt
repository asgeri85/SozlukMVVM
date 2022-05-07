package com.example.sozlukmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sozlukmvvm.model.WordResponseModel
import com.example.sozlukmvvm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _wordData = MutableLiveData<WordResponseModel?>()
    val wordData: LiveData<WordResponseModel?>
        get() = _wordData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    init {
        getAllWord()
    }

    private fun getAllWord() {
        viewModelScope.launch {
            _loading.value = true
            val request = homeRepository.getWordData()
            when (request) {
                is NetworkResult.Success -> {
                    _wordData.value = request.data
                    _loading.value = false
                }
                is NetworkResult.Error -> {
                    _error.value = request.message
                    _loading.value = false
                }
            }
        }
    }

    fun getSearchWord(ing:String){
        viewModelScope.launch {
            _loading.value=true
            val request=homeRepository.getSearchWord(ing)
            when(request){
                is NetworkResult.Success->{
                    _wordData.value=request.data
                    _loading.value=false
                }
                is NetworkResult.Error->{
                    _error.value=request.message
                    _loading.value=false
                }
            }
        }
    }
}