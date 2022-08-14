package com.rinatvasilev.httpclient

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val httpClient = HttpClient.Builder()
            .baseUrl("test base url")
            .build()

        val httpClientInterfaceImpl: SomeService = httpClient.create()
        httpClientInterfaceImpl.getList(id = 123)

        //todo init keystore
    }
}

interface SomeService {
    @Post("some url here")
    fun getList(@Param("id") id: Long)
}