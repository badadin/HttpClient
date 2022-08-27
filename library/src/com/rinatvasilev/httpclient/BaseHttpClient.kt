package com.rinatvasilev.httpclient

interface BaseHttpClient {
    fun get(request: String, params: Map<String, Any>? = null): HttpClientResponse
    fun post(request: String, params: Map<String, Any>? = null): HttpClientResponse
}
