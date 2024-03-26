package com.example.news_project.api_service

import com.example.news_project.model.DetailItem
import com.example.news_project.model.ListNewsFeed
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApi {
    @GET("newsfeed.json")
    suspend fun getNewsFeed(): Response<ListNewsFeed>

    @GET("detail.json")
    suspend fun getDetail(): DetailItem

    companion object {
        operator fun invoke(): NewsApi {
            return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Akaizz/static/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }
    }
}