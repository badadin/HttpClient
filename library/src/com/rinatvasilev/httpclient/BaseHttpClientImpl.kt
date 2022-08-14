package com.rinatvasilev.httpclient

import android.util.Log

class BaseHttpClientImpl : BaseHttpClient {
    override fun post(request: String, params: Map<String, Any>?) {
        Log.d("abcd", "post: $request, params: $params")
    }
}
