package com.example.githubappcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.githubappcompose.R

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null,
            modifier = Modifier.size(122.dp)
        )
        Text(
            text = "Refresh",
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = modifier
                .padding(top = 4.dp)
        )
    }
}