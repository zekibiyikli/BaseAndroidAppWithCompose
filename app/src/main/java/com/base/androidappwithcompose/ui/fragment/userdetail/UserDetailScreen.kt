package com.base.androidappwithcompose.ui.fragment.userdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UserDetailScreen(
    userId: String,
    viewModel: UserDetailViewModel = hiltViewModel(),
    navController: NavController,
    onHideBottomBar: () -> Unit
) {
    LaunchedEffect(Unit) {
        onHideBottomBar()
    }

    Box(modifier = Modifier.fillMaxSize())  {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)   // ✔ Artık çalışır
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        Text(modifier = Modifier.align(Alignment.Center), text = userId, fontSize = 16.sp, color = Color(0xFF03DAC5),)

    }

}
