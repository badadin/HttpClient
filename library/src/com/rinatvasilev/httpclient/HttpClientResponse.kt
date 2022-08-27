package com.rinatvasilev.httpclient

class HttpClientResponse(
    val code: Int = 0,
    val message: String? = "",
    val headers: Map<String, List<String>>? = hashMapOf(),
    val body: ByteArray? = null
)
