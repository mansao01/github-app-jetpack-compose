package com.example.githubappcompose.ui.screen.detail.following

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
import com.example.githubappcompose.ui.common.FollowingUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FollowingViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    var uiState: FollowingUiState by mutableStateOf(FollowingUiState.Loading)
        private set

    fun getFollowing(username: String) {
        viewModelScope.launch {
            uiState = FollowingUiState.Loading
            uiState = try {
                val result = userRepository.getFollowing(username)
                FollowingUiState.Success(result)
            } catch (e: IOException) {
                FollowingUiState.Error
            } catch (e: HttpException) {
                FollowingUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GitHubApplication)
                val userRepository = application.container.userRepository
                FollowingViewModel(userRepository = userRepository)
            }
        }
    }
}