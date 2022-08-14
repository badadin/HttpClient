package com.rinatvasilev.httpclient.main

import android.content.Context
import android.content.res.Configuration
import android.os.*
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
fun MainScreen(
    app: App,
    navController: NavController,
    activityContext: Context,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    //todo vm

    val backToExit = remember { mutableStateOf(false) }
    val tapToExitText = stringResource(R.string.tapToExit)

    BackPressedHandler(onBackPressed = {
        if (backToExit.value) {
            (activityContext as? MainActivity)?.finish()
        } else {
            backToExit.value = true
            Toast.makeText(activityContext, tapToExitText, Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ backToExit.value = false }, 2000)
        }
    })

    MainScreenUi(
        onPersonalizeClicked = { navController.navigate(Screens.DETAILS.name) },
        isDarkTheme = isDarkTheme
    )
}

@Composable
private fun MainScreenUi(
    onPersonalizeClicked: () -> Unit,
    isDarkTheme: Boolean
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Toolbar(
                title = stringResource(R.string.appName),
                isDarkTheme = isDarkTheme
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                onClick = { onPersonalizeClicked() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorAccent,
                    contentColor = colorLight
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = stringResource(R.string.appName), fontSize = 18.sp)
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .height(48.dp),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 2.dp, color = colorRed),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = colorRed)
            ) {
                Text(text = stringResource(R.string.appName), fontSize = 18.sp)
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
private fun LoginScreenPreview() {
    HttpClientTheme {
        MainScreenUi(
            onPersonalizeClicked = {},
            isDarkTheme = isSystemInDarkTheme()
        )
    }
}
