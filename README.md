### Simple Retrofit-like HTTP library
Based on Java Dynamic Proxy <br/>
Written in educational purposes only <br/>
Using [Denom common library](https://github.com/Digrol/Denom/blob/master/libs/org.denom.common-2021.02.12.jar) for networking

### Usage
Init HttpClient
```
 val httpClient = HttpClient.Builder()
    .baseUrl("https://cataas.com")
    .build()
```
###
Create service interface
```
interface CatService {
    @Get("cat")
    fun getCatById(@Param("id") id: Long): HttpClientResponse

    @Get("api/cats")
    fun getCuteCats(@Param("tags") tags: String, @Param("limit") limit: Int): HttpClientResponse
}
```
###
Prepare service interface to use
```
val catService: CatService = httpClient.create()
```
