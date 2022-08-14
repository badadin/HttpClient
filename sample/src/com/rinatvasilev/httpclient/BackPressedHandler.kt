package com.rinatvasilev.httpclient

import androidx.activity.*
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.*

@Composable
fun BackPressedHandler(
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed = rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed.value()
            }
        }
    }

    DisposableEffect(backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
