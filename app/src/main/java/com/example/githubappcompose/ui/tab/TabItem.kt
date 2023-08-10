package com.example.githubappcompose.ui.tab

import androidx.compose.runtime.Composable


typealias ComposableFun = @Composable () -> Unit

data class TabItem(var title: String, var screen: ComposableFun)