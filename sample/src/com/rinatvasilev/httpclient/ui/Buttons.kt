package com.rinatvasilev.httpclient.ui

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.rinatvasilev.httpclient.ui.theme.*

@Composable
fun TxtBtn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    isDarkTheme: Boolean
) {
    val colorText = if (enabled) {
        colorAccent
    } else {
        if (isDarkTheme) colorGrayTextDark else colorGrayTextLight
    }

    Btn(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        isDarkTheme = isDarkTheme
    ) {
        Text(text = text, color = colorText, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun RadioBtn(
    isChecked: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isDarkTheme: Boolean
) {
    val colorRadio = if (enabled) {
        colorAccent
    } else {
        if (isDarkTheme) colorGrayTextDark else colorGrayTextLight
    }

    Btn(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        isDarkTheme = isDarkTheme
    ) {
        Row {
            RadioButton(
                selected = isChecked,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorRadio,
                    unselectedColor = colorRadio.copy(alpha = 0.6f),
                    disabledColor = colorRadio.copy(alpha = ContentAlpha.disabled)
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = text, color = colorRadio, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun Btn(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    useExternalPadding: Boolean = true,
    useInnerPadding: Boolean = true,
    isDarkTheme: Boolean,
    content: @Composable RowScope.() -> Unit
) {
    val colorBackground = if (enabled) {
        if (isDarkTheme) colorControlDark else colorControlLight
    } else {
        if (isDarkTheme) colorDisabledDark else colorDisabledLight
    }

    val externalPadding = if (useExternalPadding) 6.dp else 0.dp
    val innerPadding = if (useInnerPadding) 16.dp else 0.dp

    Row(
        modifier = modifier
            .height(48.dp)
            .padding(top = externalPadding, bottom = externalPadding)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorBackground)
            .clickable { if (enabled) onClick() }
            .padding(start = innerPadding, end = innerPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
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
private fun TxtBtnPreview(isDarkTheme: Boolean = isSystemInDarkTheme()) {
    HttpClientTheme {
        TxtBtn(onClick = {}, text = "Test", isDarkTheme = isDarkTheme)
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
private fun RadioBtnPreview(isDarkTheme: Boolean = isSystemInDarkTheme()) {
    HttpClientTheme {
        RadioBtn(
            isChecked = true,
            onClick = {},
            enabled = true,
            text = "Test",
            isDarkTheme = isDarkTheme
        )
    }
}