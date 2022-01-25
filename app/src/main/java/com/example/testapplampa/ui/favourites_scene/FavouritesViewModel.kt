package com.example.testapplampa.ui.favourites_scene

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplampa.repository.MainRepository
import com.example.testapplampa.retrofit.response.FilmsDetailsItem
import kotlinx.coroutines.*

class FavouritesViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val topNewsList = MutableLiveData<List<FilmsDetailsItem>>()
    val favouritesNewsList = MutableLiveData<List<FilmsDetailsItem>>()
    var job1 : Job? = null
    var job2 : Job? = null

    fun getTopNews(){
        job1 = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getTopNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    topNewsList.postValue(response.body()?.filter {
                        it.top == 1 && it.type == "favourites"
                    })
                    //newsList.postValue(response.body())
                } else Log.d("AAA", response.message())
            }
        }
    }

    fun getFavouritesNews(){
        job2 = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getStoriesNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    favouritesNewsList.postValue(response.body()?.filter {
                        it.type == "favourites"
                    })
                    //newsList.postValue(response.body())
                } else Log.d("AAA", response.message())
            }
        }
    }
}