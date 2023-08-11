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
import com.example.githubappcompose.ui.common.HomeUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set



    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                val result = userRepository.getUsers()
                HomeUiState.Success(result)
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun searchUser(query: String) {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                val result = userRepository.searchUser(query)
                HomeUiState.SuccessSearch(result.items)
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GitHubApplication)
                val userRepository = application.container.userRepository
                HomeViewModel(userRepository = userRepository)
            }
        }
    }
}