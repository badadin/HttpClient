package com.rinatvasilev.httpclient.main

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rinatvasilev.httpclient.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.ref.WeakReference

class CatListViewModel(val app: App, activityContext: WeakReference<Context>) : ViewModel() {

    val catList = mutableStateListOf<CatInfo>()
    var showProgress = mutableStateOf(false)

    fun getCats() {
        catList.clear()
        catList.addAll(arrayListOf(
            CatInfo(id = "595f280c557291a9750ebf80", arrayListOf("cute")),
            CatInfo(id = "595f280e557291a9750ebf9f", arrayListOf("cute")),
            CatInfo(id = "595f280e557291a9750ebfa6", arrayListOf("cute"))
        ))

//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//
//                // we will not use layered architecture in this example for simplicity
//                app.catService?.getCuteCats(tags = "cute", limit = 10)?.let { response ->
//
//                    val json = JSONObject(response.responseJson)
//                    //todo use gson
//
//                    //val cats = app.catService?.getCuteCats(tags = "cute", limit = 10)
//
//                    withContext(Dispatchers.Main) {
//                        catList.clear()
//                        //todo catList.addAll(cats)
//                    }
//                }
//            }
//        }
    }
}
