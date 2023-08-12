package com.example.githubappcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubappcompose.R
import com.example.githubappcompose.network.response.SearchItems
import com.example.githubappcompose.ui.screen.home.HomeViewModel
import com.example.githubappcompose.ui.screen.home.UserSearchListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            homeViewModel.searchUser(newQuery)
        },
        active = false,
        onActiveChange = { isActive = it },
        placeholder = { Text(text = stringResource(R.string.search_user)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (isActive) {
                IconButton(
                    onClick = {
                        if (query.isNotEmpty()) {
                            query = ""
                            homeViewModel.getUsers()
                        } else isActive = false
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            }
        },
        modifier = modifier
    ) {}

}