package com.example.testapplampa.retrofit.Api

import com.example.testapplampa.retrofit.response.FilmsDetails
import com.example.testapplampa.retrofit.response.FilmsDetailsItem
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import kotlin.coroutines.ContinuationInterceptor

const val BASE_URL ="http://188.40.167.45:3001"

interface FilmsApiService {

    /*@GET("/")
    fun getNews(@QueryMap parameters : HashMap<String,String>): Call<List<FilmsDetails>>*/
    @GET("/")
    suspend fun getAllNews(): Response<List<FilmsDetailsItem>>

    @GET("/")
    suspend fun getTopNews(): Response<List<FilmsDetailsItem>>

    @GET("/")
    suspend fun getStoriesNews(): Response<List<FilmsDetailsItem>>

    companion object {
        operator fun invoke(): FilmsApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FilmsApiService::class.java)
    }
}