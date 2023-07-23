package com.example.githubappcompose.ui.common

import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem

sealed interface HomeUiState {
    data class Success(val Users: List<UserResponseItem>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

sealed interface DetailUiState {
    data class Success(val Users: UserDetailResponse) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}