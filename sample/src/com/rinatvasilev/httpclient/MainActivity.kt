package com.rinatvasilev.httpclient

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rinatvasilev.httpclient.details.DetailsScreen
import com.rinatvasilev.httpclient.main.MainScreen
import com.rinatvasilev.httpclient.ui.theme.HttpClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as App

        setContent {
            this.window.statusBarColor = if (isSystemInDarkTheme()) {
                ContextCompat.getColor(this, R.color.colorStatusBarDark)
            } else {
                ContextCompat.getColor(this, R.color.colorStatusBarLight)
            }

            Navigation(app, this@MainActivity)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Navigation(app: App, activityContext: Context) {
    val navController = rememberNavController()
    val currentScreen = Screens.fromRoute(navController.currentBackStackEntryAsState().value?.destination?.route)

    HttpClientTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            NavHost(navController = navController, startDestination = Screens.MAIN.name) {

                composable(Screens.MAIN.name) {
                    MainScreen(
                        app = app,
                        navController = navController,
                        activityContext = activityContext
                    )
                }

                composable(Screens.DETAILS.name) {
                    DetailsScreen(
                        app = app,
                        navController = navController,
                        activityContext = activityContext
                    )
                }
            }
        }
    }
}

@Composable
fun ObserveLifeCycle(
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnResume by rememberUpdatedState(onResume)
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnStop by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> currentOnStart()
                Lifecycle.Event.ON_RESUME -> currentOnResume()
                Lifecycle.Event.ON_PAUSE -> currentOnPause()
                Lifecycle.Event.ON_STOP -> currentOnStop()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
