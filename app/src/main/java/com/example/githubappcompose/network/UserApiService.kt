package com.example.githubappcompose.network

import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    @Headers("authorization: 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 ")
    suspend fun getUsers(
//        @Header("Authorization")
//        token: String = "Bearer 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"
    ): List<UserResponseItem>

    @GET("users/{username}")
    @Headers("authorization: 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 ")
    suspend fun getDetailUser(
        @Path("username")
        username: String,
    ): UserDetailResponse
}