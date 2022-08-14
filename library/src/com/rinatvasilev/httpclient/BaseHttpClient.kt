package com.rinatvasilev.httpclient

interface BaseHttpClient {
    fun post(request: String, params: Map<String, Any>? = null)
}
