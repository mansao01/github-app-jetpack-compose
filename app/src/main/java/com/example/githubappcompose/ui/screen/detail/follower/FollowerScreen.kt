package com.example.githubappcompose.ui.screen.detail.follower

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubappcompose.network.response.FollowerResponseItem
import com.example.githubappcompose.ui.common.FollowerUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.FollowerItem
import com.example.githubappcompose.ui.component.LoadingScreen


@Composable
fun FollowerScreen(
    uiState: FollowerUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is FollowerUiState.Loading -> LoadingScreen()
        is FollowerUiState.Success -> FollowerListItem(user = uiState.users, modifier = modifier)

        is FollowerUiState.Error -> ErrorScreen()
    }
}

@Composable
fun FollowerListItem(
    user: List<FollowerResponseItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(user) { data ->
            FollowerItem(user = data)
        }
    }
}