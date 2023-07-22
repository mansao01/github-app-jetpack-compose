package com.example.githubappcompose

import android.app.Application
import com.example.githubappcompose.data.AppContainer
import com.example.githubappcompose.data.DefaultAppContainer

class GitHubApplication :Application(){
    lateinit var container:AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}