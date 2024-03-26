package com.example.news_project.model

import com.google.gson.annotations.SerializedName

data class NewsFeedItem(
    @SerializedName("document_id")
    val documentId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("content_type")
    val contentType: String,
    @SerializedName("images")
    val images: List<Image>?
)

data class Image(
    @SerializedName("href")
    val href: String,
    @SerializedName("main_color")
    val mainColor: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)
