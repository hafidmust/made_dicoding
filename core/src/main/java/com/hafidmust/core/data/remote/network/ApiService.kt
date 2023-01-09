package com.hafidmust.core.data.remote.network

import com.hafidmust.core.data.remote.response.ListStoriesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("stories")
    suspend fun getList(
        @Header("Authorization") token: String
    ): ListStoriesResponse
}