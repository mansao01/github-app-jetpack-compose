package com.example.githubappcompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.githubappcompose.R
import com.example.githubappcompose.ui.screen.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { newQuery ->
            homeViewModel.searchUser(newQuery)
        },
        active = isActive,
        onActiveChange = { isActive = it },
        placeholder = { Text(text = stringResource(R.string.search_user)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (isActive) {
                IconButton(
                    onClick = { if (query.isNotEmpty()) query = "" else isActive = false }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            }
        },
        modifier = modifier
    ) {

    }

}