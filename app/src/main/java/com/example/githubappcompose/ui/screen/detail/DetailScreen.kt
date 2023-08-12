@file:OptIn(ExperimentalPagerApi::class)

package com.example.githubappcompose.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubappcompose.R
import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.ui.common.DetailUiState
import com.example.githubappcompose.ui.common.FollowerUiState
import com.example.githubappcompose.ui.common.FollowingUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.LoadingScreen
import com.example.githubappcompose.ui.screen.detail.follower.FollowerScreen
import com.example.githubappcompose.ui.screen.detail.follower.FollowerViewModel
import com.example.githubappcompose.ui.screen.detail.following.FollowingScreen
import com.example.githubappcompose.ui.screen.detail.following.FollowingViewModel
import com.example.githubappcompose.ui.tab.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    uiState: DetailUiState,
    followerUiState: FollowerUiState,
    followingUiState: FollowingUiState,
    username: String,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory),
    followingViewModel: FollowingViewModel = viewModel(factory = FollowingViewModel.Factory),
    followerVieModel: FollowerViewModel = viewModel(factory = FollowerViewModel.Factory),
    navigateToHome: () -> Unit

) {
    LaunchedEffect(Unit) {
        detailViewModel.getDetailUser(username)
        followingViewModel.getFollowing(username)
        followerVieModel.getFollower(username)
    }
    when (uiState) {
        is DetailUiState.Loading -> LoadingScreen()
        is DetailUiState.Success -> DetailContent(
            navigateToHome = navigateToHome,
            user = uiState.users,
            followerUiState = followerUiState,
            followingUiState = followingUiState
        )

        is DetailUiState.Error -> ErrorScreen()
    }

}

@Composable
fun DetailContent(
    user: UserDetailResponse,
    navigateToHome: () -> Unit,
    followerUiState: FollowerUiState,
    followingUiState: FollowingUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(top = 16.dp)
                .clickable {
                    navigateToHome()
                })
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.padding(10.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(160.dp)

            )
            Text(text = user.name, modifier = Modifier.padding(top = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(
                    text = user.location.toString(),
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.LocationCity, contentDescription = null)
                Text(
                    text = user.company.toString(),
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        TabFollowSection(
            followerUiState = followerUiState,
            followingUiState = followingUiState
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabFollowSection(
    modifier: Modifier = Modifier,
    followerUiState: FollowerUiState,
    followingUiState: FollowingUiState,
) {
    val tabs = listOf(
        TabItem("Follower") { FollowerScreen(uiState = followerUiState) },
        TabItem("Following") { FollowingScreen(uiState = followingUiState) },
    )

    val pagerState = rememberPagerState(initialPage = tabs.size)
    Column(modifier = modifier.fillMaxWidth()) {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }

}

@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}