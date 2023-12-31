package com.example.githubappcompose.ui.screen.detail.follower

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubappcompose.GitHubApplication
import com.example.githubappcompose.data.UserRepository
import com.example.githubappcompose.ui.common.FollowerUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FollowerViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var uiState: FollowerUiState by mutableStateOf(FollowerUiState.Loading)
        private set

    fun getFollower(username: String) {
        viewModelScope.launch {
            uiState = FollowerUiState.Loading
            uiState = try {
                val result = userRepository.getFollower(username)
                FollowerUiState.Success(result)
            } catch (e: IOException) {
                FollowerUiState.Error
            } catch (e: HttpException) {
                FollowerUiState.Error
            }
        }
    }

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
