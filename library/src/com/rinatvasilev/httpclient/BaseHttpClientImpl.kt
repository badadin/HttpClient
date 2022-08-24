package com.rinatvasilev.httpclient

import android.util.Log
import org.denom.format.JSONObject
import org.denom.net.http.HttpClient
import org.denom.net.http.HttpRequest
import org.denom.net.http.HttpResponse

/**
 * I've used [org.denom.net.http.RestClient](https://github.com/Digrol/Denom).
 * You can use any else such as OkHttp or your own HttpURLConnection implementation for example.
 * */
class BaseHttpClientImpl(private val baseUrl: String) : BaseHttpClient {

    private val httpClient = HttpClient()

    override fun get(request: String, params: Map<String, Any>?): HttpResponse {
        Log.d("abcd", "get: $request, params: $params")

        val paramList = Array<Array<String>>(params?.entries?.size ?: 0) { Array(2) { "" } }
        params?.let {
            for ((index, param) in it.entries.withIndex()) {
                paramList[index] = arrayOf(param.key, param.value.toString())
            }
        }

        Log.d("abcd", "paramList:")
        paramList.forEach {
            Log.d("abcd", "${it[0]} and ${it[1]}")
        }

        val queryURL = formQueryUrl(request, queryParams = paramList.map { it }.toTypedArray())
        val httpRequest = HttpRequest(queryURL, "GET")
        httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        return httpClient.transmit(httpRequest)
    }

    override fun post(request: String, params: Map<String, Any>?): HttpResponse {
        Log.d("abcd", "post: $request, params: $params")

        val jsonRequest = JSONObject()
        params?.let {
            for (param in it.entries) {
                jsonRequest.put(param.key, param.value)
            }
        }
        Log.d("abcd", "jsonRequest: $jsonRequest")

        val httpRequest = HttpRequest("$baseUrl/$request", "POST")
        httpRequest.setBody(jsonRequest)
        httpRequest.addHeader("Content-Type", "application/json; charset=utf-8")
        httpRequest.addHeader("Content-Length", httpRequest.body.size.toString())
        return httpClient.transmit(httpRequest)
    }

    private fun formQueryUrl(requestPath: String, vararg queryParams: Array<String>) =
        HttpRequest.formQueryUrl("$baseUrl/$requestPath", *queryParams)
}
