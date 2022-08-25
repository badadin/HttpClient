package com.rinatvasilev.httpclient

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class HttpClientInvocationHandler(private val clientImpl: BaseHttpClient) : InvocationHandler {

    // todo
    //  можно сделать фабрику для кэширования запросов, пример тут:
    //  https://github.com/androidbroadcast/DynamicProxySample

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
        when (method.annotations.firstOrNull()) {
            is Get -> invokeGet(method, args)
            is Post -> invokePost(method, args)
            else -> throw IllegalStateException("Unknown annotation")
        }
        return Unit
    }

    private fun invokeGet(method: Method, args: Array<out Any>?) {
        if (BuildConfig.DEBUG) {
            checkGet(method)
        }

        val request = method.annotations.firstNotNullOf { it as? Get }.value
        clientImpl.get(request, buildParams(method, args))
    }

    private fun invokePost(method: Method, args: Array<out Any>?) {
        if (BuildConfig.DEBUG) {
            checkPost(method)
        }

        val request = method.annotations.firstNotNullOf { it as? Post }.value
        clientImpl.post(request, buildParams(method, args))
    }

    private fun buildParams(method: Method, args: Array<out Any>?): Map<String, Any>? {
        var parameters: Map<String, Any>? = null
        args?.let { arguments ->
            val paramNames = method.parameterAnnotations.map { params ->
                params.firstNotNullOf { it as? Param }.value
            }
            parameters = buildMap {
                repeat(arguments.size) { index ->
                    put(paramNames[index], args[index])
                }
            }
        }
        return parameters
    }

    private fun checkPost(method: Method) {
        check(method.annotations.any { it is Post }) {
            "Function has no Post annotation"
        }

        // if any parameterAnnotation exists, it must be Param
        method.parameterAnnotations.forEach { paramAnnotations ->
            check(paramAnnotations.any { it is Param }) {
                "Function Post has no Param annotation"
            }
        }
    }

    private fun checkGet(method: Method) {
        check(method.annotations.any { it is Get }) {
            "Function has no Get annotation"
        }

        // if any parameterAnnotation exists, it must be Param
        method.parameterAnnotations.forEach { paramAnnotations ->
            check(paramAnnotations.any { it is Param }) {
                "Function Get has no Param annotation"
            }
        }
    }
}
