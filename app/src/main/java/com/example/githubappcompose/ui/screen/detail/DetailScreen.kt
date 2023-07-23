package com.example.githubappcompose.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.githubappcompose.network.response.UserDetailResponse
import com.example.githubappcompose.ui.common.DetailUiState
import com.example.githubappcompose.ui.common.HomeUiState
import com.example.githubappcompose.ui.component.ErrorScreen
import com.example.githubappcompose.ui.component.LoadingScreen

@Composable
fun DetailScreen(
    uiState: DetailUiState
) {
    when (uiState) {
        is DetailUiState.Loading -> LoadingScreen()
        is DetailUiState.Success -> DetailContent(
            user = uiState.Users
        )

        is DetailUiState.Error -> ErrorScreen()
    }

}

@Composable
fun DetailContent(
    user: UserDetailResponse,
    modifier: Modifier = Modifier
) {
    Text(text = "Helo ${user.email}")
}