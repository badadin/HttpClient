package com.rinatvasilev.httpclient.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.rinatvasilev.httpclient.App
import com.rinatvasilev.httpclient.ui.theme.colorControlDark
import com.rinatvasilev.httpclient.ui.theme.colorControlLight
import com.rinatvasilev.httpclient.ui.theme.colorMainTextDark
import com.rinatvasilev.httpclient.ui.theme.colorMainTextLight

@Composable
fun MainListItem(
    catInfo: CatInfo,
    onItemClicked: (String) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    Row(
        modifier = Modifier
            .height(150.dp)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = if (isDarkTheme) {
                    colorControlDark
                } else {
                    colorControlLight
                }
            )
            .clickable { onItemClicked(catInfo.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = "${App.BASE_URL}/cat?id=${catInfo.id}",
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "TEST",
                fontSize = 20.sp,
                color = if (isDarkTheme) colorMainTextDark else colorMainTextLight
            )
            Text(
                text = "Test",
                fontSize = 14.sp,
                color = if (isDarkTheme) colorMainTextDark else colorMainTextLight
            )
        }
    }
}
