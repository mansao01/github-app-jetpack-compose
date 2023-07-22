package com.example.githubappcompose.ui.common

import com.example.githubappcompose.network.response.UserResponseItem

sealed interface UiState {
    data class Success(val Users: List<UserResponseItem>) : UiState
    object Error : UiState
    object Loading : UiState
}