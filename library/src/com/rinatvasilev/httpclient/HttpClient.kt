package com.rinatvasilev.httpclient

import java.lang.reflect.Proxy
import kotlin.properties.Delegates

class HttpClient private constructor(private val clientImpl: BaseHttpClient) {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> create(cls: Class<T>): T {
        return Proxy.newProxyInstance(
            cls.classLoader,
            arrayOf(cls),
            HttpClientInvocationHandler(clientImpl = clientImpl)
        ) as T
    }

    class Builder {
        private var clientImpl: BaseHttpClient? = null
        private var baseUrl: String by Delegates.notNull()

        fun baseUrl(url: String): Builder {
            this.baseUrl = url
            return this
        }

        fun rootCertificate(certificate: Any): Builder {
            //todo set root certificate
            return this
        }

        fun httpClientImpl(clientImpl: BaseHttpClient): Builder {
            this.clientImpl = clientImpl
            return this
        }

        fun build(): HttpClient {
            return HttpClient(clientImpl = if (clientImpl != null) clientImpl!! else BaseHttpClientImpl(baseUrl))
        }
    }
}

inline fun <reified T : Any> HttpClient.create(): T {
    return create(T::class.java)
}
