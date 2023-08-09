package com.example.githubappcompose.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubappcompose.network.response.UserResponseItem
import com.example.githubappcompose.ui.common.HomeUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.LoadingScreen
import com.example.githubappcompose.ui.component.UserItem


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> UserListItem(
            user = uiState.users,
            navigateToDetail = navigateToDetail
        )

        is HomeUiState.Error -> ErrorScreen()
    }

}

@Composable
fun UserListItem(
    user: List<UserResponseItem>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn() {
        items(user) { data ->
            UserItem(user = data, modifier = modifier.clickable { navigateToDetail(data.login) })
        }
    }

}


