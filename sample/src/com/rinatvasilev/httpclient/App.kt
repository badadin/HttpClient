package com.rinatvasilev.httpclient

import android.app.Application
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val httpClient = HttpClient.Builder()
            .baseUrl("https://cataas.com")
            .build()

        val httpClientInterfaceImpl: SomeService = httpClient.create()

        Thread {
            try {
                httpClientInterfaceImpl.getRandomCuteCatWithGet(id = 123, name = "Cat")
            } catch (ex: Throwable) {
                Log.e("abcd", ex.toString())
            }

            try {
                httpClientInterfaceImpl.getRandomCuteCatWithPost(id = 123)
            } catch (ex: Throwable) {
                Log.e("abcd", ex.toString())
            }

        }.start()

        //todo init keystore
    }
}

interface SomeService {
    @Post("cat/cute")
    fun getRandomCuteCatWithPost(@Param("id") id: Long)

    @Get("cat/cute")
    fun getRandomCuteCatWithGet(@Param("id") id: Long, @Param("name") name: String)
}
