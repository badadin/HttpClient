package com.rinatvasilev.httpclient.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.rinatvasilev.httpclient.App
import com.rinatvasilev.httpclient.ui.theme.*

@Composable
fun CatListItem(
    catInfo: CatInfo,
    onItemClicked: (String) -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
            modifier = Modifier
                .padding(start = 16.dp)
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
            model = "${App.BASE_URL}/cat?id=${catInfo.id}",
            loading = { CircularProgressIndicator() },
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        LazyRow {
            items(catInfo.tags) { item ->
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .height(36.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = if (isDarkTheme) colorChipDark else colorChipLight)
                        .padding(start = 8.dp, end = 8.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        fontSize = 18.sp,
                        color = if (isDarkTheme) colorMainTextDark else colorMainTextLight
                    )
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
private fun CatListItemPreview() {
    HttpClientTheme {
        val catInfo = CatInfo("", arrayListOf("Cat", "Cute"))
        CatListItem(catInfo = catInfo, onItemClicked = {}, isDarkTheme = isSystemInDarkTheme())
    }
}
