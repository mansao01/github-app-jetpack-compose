package com.example.githubappcompose.network

import com.example.githubappcompose.network.response.UserResponseItem
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUsers():List<UserResponseItem>
}