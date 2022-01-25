package com.example.testapplampa.repository

import com.example.testapplampa.retrofit.Api.FilmsApiService

class MainRepository(private val filmsApiService: FilmsApiService) {

    suspend fun getAllNews() = filmsApiService.getAllNews()

    suspend fun getTopNews() = filmsApiService.getTopNews()

    suspend fun getStoriesNews() = filmsApiService.getStoriesNews()
}