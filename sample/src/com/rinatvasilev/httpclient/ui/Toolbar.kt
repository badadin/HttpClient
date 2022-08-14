package com.rinatvasilev.httpclient.ui

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.rinatvasilev.httpclient.ui.theme.*

@Composable
fun Toolbar(
    title: String,
    navigationIcon: (@Composable RowScope.() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    isDarkTheme: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = if (isDarkTheme) colorDark else colorLight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var textStartPadding = 16.dp

        if (navigationIcon != null) {
            Row(Modifier.padding(start = 4.dp, end = 4.dp)) {
                navigationIcon()
            }
            textStartPadding = 0.dp
        }

        Box(
            Modifier
                .weight(1f)
                .padding(end = 8.dp, start = textStartPadding)
        ) {
            Text(
                text = title,
                color = if (isDarkTheme) colorMainTextDark else colorMainTextLight,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

        Row(Modifier.padding(end = 16.dp)) {
            actions()
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
private fun ToolbarPreview(isDarkTheme: Boolean = isSystemInDarkTheme()) {
    HttpClientTheme {
        Toolbar(
            title = "Toolbar",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Menu, contentDescription = null,
                        tint = if (isDarkTheme) colorMainTextDark else colorMainTextLight
                    )
                }
            },
            actions = {
                TxtBtn(
                    onClick = {},
                    text = "Test",
                    isDarkTheme = isDarkTheme
                )
            },
            isDarkTheme = isDarkTheme
        )
    }
}
