package com.rinatvasilev.httpclient.details

import android.content.Context
import android.content.res.Configuration
import android.os.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.rinatvasilev.httpclient.*
import com.rinatvasilev.httpclient.R
import com.rinatvasilev.httpclient.ui.*
import com.rinatvasilev.httpclient.ui.theme.*

@Composable
fun DetailsScreen(
    app: App,
    navController: NavController,
    activityContext: Context,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    //todo vm

    DetailsScreenUi(
        onBackClicked = { navController.popBackStack() },
        isDarkTheme = isDarkTheme
    )
}

@Composable
private fun DetailsScreenUi(
    onBackClicked: () -> Unit,
    isDarkTheme: Boolean
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Toolbar(
                title = stringResource(R.string.appName),
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            Icons.Filled.ArrowBack, contentDescription = null,
                            tint = if (isDarkTheme) colorMainTextDark else colorMainTextLight
                        )
                    }
                },
                isDarkTheme = isDarkTheme
            )
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
private fun LoginScreenPreview() {
    HttpClientTheme {
        DetailsScreenUi(
            onBackClicked = {},
            isDarkTheme = isSystemInDarkTheme()
        )
    }
}
