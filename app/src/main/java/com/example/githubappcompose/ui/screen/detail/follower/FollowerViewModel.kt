package com.example.githubappcompose.ui.screen.detail.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubappcompose.GitHubApplication
import com.example.githubappcompose.data.UserRepository

class FollowerViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GitHubApplication)
                val userRepository = application.container.userRepository
                FollowerViewModel(userRepository = userRepository)
            }
        }
    }
}
