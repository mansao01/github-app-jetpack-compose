package com.example.githubappcompose.data

import com.example.githubappcompose.network.UserApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userRepository: UserRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }


}