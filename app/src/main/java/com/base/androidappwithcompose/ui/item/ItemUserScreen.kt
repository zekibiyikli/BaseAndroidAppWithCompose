package com.base.androidappwithcompose.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.base.androidappwithcompose.R

@Composable
fun UserItem(name: String, city: String, imageUrl: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 10.dp).padding(start = 20.dp, end = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if (imageUrl.isNullOrEmpty()) {
                painterResource(id = R.drawable.ic_launcher_foreground)
            } else {
                rememberAsyncImagePainter(imageUrl)
            },
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontSize = 16.sp, color = Color(0xFF03DAC5),)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = city, fontSize = 16.sp, color = Color(0xFF03DAC5),)
        }
    }
    HorizontalDivider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(start = 100.dp).fillMaxWidth()
    )
}
