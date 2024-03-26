package com.example.news_project.model

import com.google.gson.annotations.SerializedName

data class ListNewsFeed(
    @SerializedName("items")
    val items: List<NewsFeedItem>
)
