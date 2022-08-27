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

interface CatService {
    @Post("cat/cute")
    fun getRandomCuteCatWithPost(@Param("id") id: Long): HttpClientResponse

    @Get("cat/cute")
    fun getRandomCuteCatWithGet(@Param("id") id: Long, @Param("name") name: String): HttpClientResponse

    @Get("cat/cute")
    fun getRandomCuteCatWithGetNoParams(): HttpClientResponse

    @Get("api/cats")
    fun getCuteCats(@Param("tags") tags: String, @Param("limit") limit: Int): HttpClientResponse
}
