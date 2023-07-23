package com.example.githubappcompose.network

import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): List<UserResponseItem>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username")
        username: String
    ): UserDetailResponse
}