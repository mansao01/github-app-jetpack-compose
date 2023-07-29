package com.example.githubappcompose.ui.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githubappcompose.GitHubApplication
import com.example.githubappcompose.data.UserRepository
import com.example.githubappcompose.network.UserApiService
import com.example.githubappcompose.ui.common.DetailUiState
import com.example.githubappcompose.ui.common.HomeUiState
import com.example.githubappcompose.ui.screen.home.HomeViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailViewModel(private val userRepository: UserRepository) :
    ViewModel() {

    var uiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set


      fun getDetailUser(username: String) {
        viewModelScope.launch {
            uiState = DetailUiState.Loading
            uiState = try {
                val result = userRepository.getDetailUser(username)
                DetailUiState.Success(result)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GitHubApplication)
                val userRepository = application.container.userRepository


                DetailViewModel(userRepository = userRepository)
            }
        }
    }

}