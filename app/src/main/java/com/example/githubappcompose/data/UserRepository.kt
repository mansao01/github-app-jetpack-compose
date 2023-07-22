package com.example.githubappcompose.data

import com.example.githubappcompose.network.UserApiService
import com.example.githubappcompose.network.response.UserResponseItem

interface UserRepository {
    suspend fun getUsers(): List<UserResponseItem>
}

class NetworkUserRepository(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun getUsers(): List<UserResponseItem> {
        return userApiService.getUsers()
    }
}