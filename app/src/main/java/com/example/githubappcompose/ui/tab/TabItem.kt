package com.example.githubappcompose.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.githubappcompose.ui.screen.detail.follower.FollowingScreen
import com.example.githubappcompose.ui.screen.detail.following.FollowerScreen


typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    object Follower : TabItem("Following", { FollowerScreen() })
    object Following : TabItem("Following", { FollowingScreen() })

}