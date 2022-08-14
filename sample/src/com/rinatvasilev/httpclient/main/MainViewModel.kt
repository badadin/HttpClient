package com.rinatvasilev.httpclient.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rinatvasilev.httpclient.App

class MainViewModel(val app: App, val navController: NavController) : ViewModel() {
    var showProgress = mutableStateOf(false)

    //todo
}
