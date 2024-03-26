package com.example.news_project.repository

import com.example.news_project.api_service.NewsApi
import com.example.news_project.model.ListNewsFeed
import retrofit2.Response


class Repository(private val newsApi: NewsApi) {
    suspend fun getNewsFeed(): Response<ListNewsFeed> {
        return newsApi.getNewsFeed()
    }
}