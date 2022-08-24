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

            try {
                httpClientInterfaceImpl.getRandomCuteCatWithGetNoParams()
            } catch (ex: Throwable) {
                Log.e("abcd", ex.toString())
            }

        }.start()

        //todo init keystore

        // https://cataas.com/cat?json=true
        /*
        {
           "id":"6167c4e8412a9f0018729ee0",
           "created_at":"2021-10-14T05:49:28.073Z",
           "tags":[
              "cat",
              "orange"
           ],
           "url":"/cat/6167c4e8412a9f0018729ee0"
        }
        */

        // https://cataas.com/cat?id=595f280c557291a9750ebf80
        // конкретная картинка с кошаком

        // https://cataas.com/api/cats?tags=cute&limit=10
        /*
        [
           {
              "id":"595f280c557291a9750ebf80",
              "created_at":"2015-11-06T18:36:37.342Z",
              "tags":[
                 "cute",
                 "eyes"
              ]
           },
           {
              "id":"595f280e557291a9750ebf9f",
              "created_at":"2016-10-09T12:51:24.421Z",
              "tags":[
                 "cute",
                 "sleeping"
              ]
           },
           {
              "id":"595f280e557291a9750ebfa6",
              "created_at":"2016-11-22T15:20:31.913Z",
              "tags":[
                 "cute",
                 "sleeping"
              ]
           }
        ]
        */
    }
}

interface SomeService {
    @Post("cat/cute")
    fun getRandomCuteCatWithPost(@Param("id") id: Long)

    @Get("cat/cute")
    fun getRandomCuteCatWithGet(@Param("id") id: Long, @Param("name") name: String)

    @Get("cat/cute")
    fun getRandomCuteCatWithGetNoParams()
}
