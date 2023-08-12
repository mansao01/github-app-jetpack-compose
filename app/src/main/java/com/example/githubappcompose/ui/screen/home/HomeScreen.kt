package com.example.githubappcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubappcompose.network.response.SearchItems
import com.example.githubappcompose.network.response.UserResponseItem
import com.example.githubappcompose.ui.common.HomeUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.LoadingScreen
import com.example.githubappcompose.ui.component.MySearchBar
import com.example.githubappcompose.ui.component.UserItem
import com.example.githubappcompose.ui.component.UserSearchItem


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {

    Column {
        MySearchBar(
            homeViewModel = homeViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        when (uiState) {
            is HomeUiState.Loading -> LoadingScreen()
            is HomeUiState.Success -> HomeContent(
                user = uiState.users,
                navigateToDetail = navigateToDetail,
                modifier = Modifier
            )

            is HomeUiState.SuccessSearch -> UserSearchListItem(
                user = uiState.users,
                navigateToDetail = navigateToDetail
            )

            is HomeUiState.Error -> ErrorScreen(
                modifier.clickable { homeViewModel.getUsers() }
            )
        }
    }
}


@Composable
fun HomeContent(
    user: List<UserResponseItem>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UserListItem(user = user, navigateToDetail = navigateToDetail, modifier = modifier)
}


@Composable
fun UserListItem(
    user: List<UserResponseItem>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(user) { data ->
            UserItem(user = data, modifier = modifier.clickable { navigateToDetail(data.login) })
        }
    }
}

@Composable
fun UserSearchListItem(
    user: List<SearchItems>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(user) { data ->
            UserSearchItem(user = data, modifier = modifier.clickable {
                data.login?.let {
                    navigateToDetail(
                        it
                    )
                }
            })
        }
    }
}



