package com.rinatvasilev.httpclient

import android.app.Application

class App : Application() {

    companion object {
        const val BASE_URL = "https://cataas.com"
    }

    var catService: CatService? = null

    override fun onCreate() {
        super.onCreate()

        val httpClient = HttpClient.Builder()
            .baseUrl(BASE_URL)
            .build()

        catService = httpClient.create()

        //todo init keystore
    }
}

interface CatService {
    @Get("cat")
    fun getCatById(@Param("id") id: Long): HttpClientResponse

    @Get("api/cats")
    fun getCuteCats(@Param("tags") tags: String, @Param("limit") limit: Int): HttpClientResponse
}
