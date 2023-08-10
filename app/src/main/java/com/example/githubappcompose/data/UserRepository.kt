package com.example.githubappcompose.data

import com.example.githubappcompose.network.UserApiService
import com.example.githubappcompose.network.response.FollowerResponseItem
import com.example.githubappcompose.network.response.FollowingResponseItem
import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem

interface UserRepository {
    suspend fun getUsers(): List<UserResponseItem>
    suspend fun getDetailUser(username: String): UserDetailResponse
    suspend fun getFollowing(username: String): List<FollowingResponseItem>
    suspend fun getFollower(username: String): List<FollowerResponseItem>
}

class NetworkUserRepository(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun getUsers(): List<UserResponseItem> {
        return userApiService.getUsers()
    }

    override suspend fun getDetailUser(username: String): UserDetailResponse {
        return userApiService.getDetailUser(username)
    }

    override suspend fun getFollowing(username: String): List<FollowingResponseItem> {
        return userApiService.getFollowing(username)
    }

    override suspend fun getFollower(username: String): List<FollowerResponseItem> {
        return userApiService.getFollower(username)
    }
}