package com.example.testapplampa.ui.stories_scene

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplampa.repository.MainRepository
import com.example.testapplampa.retrofit.Api.FilmsApiService
import com.example.testapplampa.retrofit.response.FilmsDetails
import com.example.testapplampa.retrofit.response.FilmsDetailsItem
import kotlinx.coroutines.*

class StoriesViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val newsList = MutableLiveData<List<FilmsDetailsItem>>()
    val topNewsList = MutableLiveData<List<FilmsDetailsItem>>()
    val storiesNewsList = MutableLiveData<List<FilmsDetailsItem>>()
    var job : Job? = null
    var job1 : Job? = null
    var job2 : Job? = null


    fun getAllNews(){
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    newsList.postValue(response.body())
                } else Log.d("AAA", response.message())
            }
        }
    }

    fun getTopNews(){
        job1 = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getTopNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    topNewsList.postValue(response.body()?.filter {
                        it.top == 1 && it.type == "strories"
                    })
                    //newsList.postValue(response.body())
                } else Log.d("AAA", response.message())
            }
        }
    }

    fun getStoriesNews(){
        job2 = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getStoriesNews()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    storiesNewsList.postValue(response.body()?.filter {
                        it.type == "strories"
                    })
                    //newsList.postValue(response.body())
                } else Log.d("AAA", response.message())
            }
        }
    }



}