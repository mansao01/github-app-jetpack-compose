@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.githubappcompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.githubappcompose.R
import com.example.githubappcompose.ui.screen.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubappcompose.ui.navigation.Screen
import com.example.githubappcompose.ui.screen.detail.DetailScreen
import com.example.githubappcompose.ui.screen.home.HomeScreen


@Composable
fun GitHubApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { GitHubTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.fillMaxSize()) {
                composable(Screen.Home.route) {
                    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
                    HomeScreen(
                        uiState = homeViewModel.uiState,
                        modifier = modifier,
                        navigateToDetail = { username ->
                            navController.navigate(Screen.Detail.createRoute(username))
                        })
                }

                composable(Screen.Detail.route, arguments = listOf(navArgument("username"){
                    type = NavType.StringType
                })) { data ->
                    val username = data.arguments?.getString("username")  ?: ""
                    DetailScreen(username = username)
                }
            }

        }
    }

}

@Composable
fun GitHubTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.githubapp),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}