package com.rinatvasilev.httpclient

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class HttpClientInvocationHandler(
    private val clientImpl: BaseHttpClient,
    private val baseUrl: String
) : InvocationHandler {

    // todo
    //  можно сделать фабрику для кэширования запросов, пример тут:
    //  https://github.com/androidbroadcast/DynamicProxySample

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
        when (method.annotations.firstOrNull()) {
            is Post -> invokePost(method, args)
            is Get -> invokeGet(method, args)
            else -> throw IllegalStateException("Unknown annotation")
        }
        return Unit
    }

    private fun invokeGet(method: Method, args: Array<out Any>?) {
        //todo
    }

    private fun invokePost(method: Method, args: Array<out Any>?) {
        if (BuildConfig.DEBUG) {
            checkPost(method)
        }

        val post = method.annotations.firstNotNullOf { it as? Post }
        val request = "$baseUrl, ${post.value}"

        if (method.parameterCount == 0) {
            clientImpl.post(request)
        } else {
            checkNotNull(args)

            val annotations = method.parameterAnnotations
            val paramNames = annotations.map { params ->
                params.firstNotNullOf { it as? Param }.value
            }
            val param = buildMap {
                repeat(method.parameterCount) { index ->
                    put(paramNames[index], args[index])
                }
            }

            clientImpl.post(request, param)
        }
    }

    private fun checkPost(method: Method) {
        check(method.annotations.any { it is Post }) {
            "Function has no Post annotation"
        }

        method.parameterAnnotations.forEach { paramAnnotaations ->
            check(paramAnnotaations.any { it is Param }) {
                "Function has no Param annotation"
            }
        }
    }
}
