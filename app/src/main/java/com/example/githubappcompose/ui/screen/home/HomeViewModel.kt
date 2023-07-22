package com.example.githubappcompose.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubappcompose.GitHubApplication
import com.example.githubappcompose.data.UserRepository
import com.example.githubappcompose.ui.common.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                val result = userRepository.getUsers()
                UiState.Success(result)
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GitHubApplication)
                val userRepository  =application.container.userRepository
                HomeViewModel(userRepository = userRepository)
            }
        }
    }
}