package com.example.githubappcompose.data

import com.example.githubappcompose.network.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val userRepository: UserRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.github.com/"
    private val gson = GsonConverterFactory.create()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(gson)
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }


}