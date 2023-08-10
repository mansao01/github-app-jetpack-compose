package com.example.githubappcompose.network

import com.example.githubappcompose.network.response.FollowerResponseItem
import com.example.githubappcompose.network.response.FollowingResponseItem
import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("users/{username}/following")
    @Headers("authorization: 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 ")
    suspend fun getFollowing(
        @Path("username")
        username: String
    ):List<FollowingResponseItem>
    @GET("users/{username}/followers")
    @Headers("authorization: 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 ")
    suspend fun getFollower(
        @Path("username")
        username: String
    ):List<FollowerResponseItem>

    @GET("search/users?")
    @Headers("authorization: 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 ")
    fun searchUser(
        @Query("q") q: String
    ): UserResponseItem
}