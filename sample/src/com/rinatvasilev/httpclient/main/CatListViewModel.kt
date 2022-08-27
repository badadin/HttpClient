package com.rinatvasilev.httpclient.main

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rinatvasilev.httpclient.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference


class CatListViewModel(val app: App, activityContext: WeakReference<Context>) : ViewModel() {

    val catList = mutableStateListOf<CatInfo>()
    var showProgress = mutableStateOf(false)

    fun getCats() {
        showProgress.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                // we will not use layered architecture in this example for simplicity
                app.catService?.getCuteCats(tags = "cute", limit = 10)?.let { response ->
                    response.body?.let {
                        val gson = Gson()
                        val userListType = object : TypeToken<ArrayList<CatResponse>>() {}.type
                        val catsResp: ArrayList<CatResponse> = gson.fromJson(String(it), userListType)

                        withContext(Dispatchers.Main) {
                            catList.clear()
                            catList.addAll(catsResp.map { catResp -> catResp.toCatInfo() })
                        }
                    }
                }

                withContext(Dispatchers.Main) {
                    showProgress.value = false
                }
            }
        }
    }
}
