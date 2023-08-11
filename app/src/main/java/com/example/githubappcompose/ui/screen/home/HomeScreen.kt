package com.example.githubappcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubappcompose.network.response.UserResponseItem
import com.example.githubappcompose.ui.common.HomeUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.LoadingScreen
import com.example.githubappcompose.ui.component.MySearchBar
import com.example.githubappcompose.ui.component.UserItem


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(
            user = uiState.users,
            navigateToDetail = navigateToDetail,
            modifier = Modifier
        )

        is HomeUiState.Error -> ErrorScreen(
            modifier.clickable { homeViewModel.getUsers() }
        )
    }

}

@Composable
fun HomeContent(
    user: List<UserResponseItem>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        MySearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        UserListItem(user = user, navigateToDetail = navigateToDetail)
    }

}

@Composable
fun UserListItem(
    user: List<UserResponseItem>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(user) { data ->
            UserItem(user = data, modifier = modifier
                .clickable { navigateToDetail(data.login) }
            )
        }
    }

}


