package com.rinatvasilev.httpclient.main

import android.content.Context
import android.content.res.Configuration
import android.os.*
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
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
import java.lang.ref.WeakReference

@Composable
fun CatListScreen(
    app: App,
    navController: NavController,
    activityContext: Context,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val vm = remember { CatListViewModel(app = app, activityContext = WeakReference(activityContext)) }

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

    LaunchedEffect(true) {
        vm.getCats()
    }

    MainScreenUi(
        catList = vm.catList,
        onCatClicked = { navController.navigate(Screens.DETAILS.name) },
        isDarkTheme = isDarkTheme
    )
}

@Composable
private fun MainScreenUi(
    catList: List<CatInfo>,
    onCatClicked: (String) -> Unit,
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

            LazyColumn {
                items(catList) {
                    MainListItem(catInfo = it, onItemClicked = { id ->
                        onCatClicked(id)
                    })
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
private fun LoginScreenPreview() {
    HttpClientTheme {
        MainScreenUi(
            catList = arrayListOf(),
            onCatClicked = {},
            isDarkTheme = isSystemInDarkTheme()
        )
    }
}
