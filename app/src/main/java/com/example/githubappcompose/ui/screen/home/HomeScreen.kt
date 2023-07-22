package com.example.githubappcompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.githubappcompose.R
import com.example.githubappcompose.network.response.UserResponseItem
import com.example.githubappcompose.ui.common.UiState
import com.example.githubappcompose.ui.component.UserItem


@Composable
fun HomeScreen(
    uiState: UiState,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> UserListItem(
            user = uiState.Users,
            navigateToDetail = navigateToDetail
        )

        is UiState.Error -> ErrorScreen()
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
            UserItem(user = data, modifier = Modifier.clickable { navigateToDetail(data.login) })
        }
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
    }
}
