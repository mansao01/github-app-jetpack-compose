package com.example.githubappcompose.ui.screen.detail.following

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubappcompose.network.response.FollowingResponseItem
import com.example.githubappcompose.ui.common.FollowingUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.FollowingItem
import com.example.githubappcompose.ui.component.LoadingScreen


@Composable
fun FollowingScreen(
    uiState: FollowingUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is FollowingUiState.Loading -> LoadingScreen()
        is FollowingUiState.Success -> FollowerListItem(
            user = uiState.users, modifier = modifier
        )

        is FollowingUiState.Error -> ErrorScreen()
    }
}

@Composable
fun FollowerListItem(
    user: List<FollowingResponseItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(user) { data ->
            FollowingItem(user = data)
        }
    }
}