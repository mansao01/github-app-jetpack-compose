package com.example.githubappcompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubappcompose.R
import com.example.githubappcompose.network.response.UserResponseItem

@Composable
fun UserItem(
    user: UserResponseItem, modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(8.dp), shape = MaterialTheme.shapes.medium) {
        Row(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier
                    .size(60.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = user.login, style = MaterialTheme.typography.titleMedium)

            }
        }
    }
}