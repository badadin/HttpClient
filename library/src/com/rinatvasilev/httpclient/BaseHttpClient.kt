package com.rinatvasilev.httpclient

import org.denom.net.http.HttpResponse

interface BaseHttpClient {
    fun get(request: String, params: Map<String, Any>? = null): HttpResponse
    fun post(request: String, params: Map<String, Any>? = null): HttpResponse
}
