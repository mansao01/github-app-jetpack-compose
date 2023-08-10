package com.example.githubappcompose.ui.common

import com.example.githubappcompose.network.response.FollowerResponseItem
import com.example.githubappcompose.network.response.FollowingResponseItem
import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.network.response.UserResponseItem

sealed interface HomeUiState {
    data class Success(val users: List<UserResponseItem>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

sealed interface DetailUiState {
    data class Success(val users: UserDetailResponse) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}
sealed interface FollowerUiState {
    data class Success(val users: List<FollowerResponseItem>) : FollowerUiState
    object Error : FollowerUiState
    object Loading : FollowerUiState
}
sealed interface FollowingUiState {
    data class Success(val users: List<FollowingResponseItem>) : FollowingUiState
    object Error : FollowingUiState
    object Loading : FollowingUiState
}