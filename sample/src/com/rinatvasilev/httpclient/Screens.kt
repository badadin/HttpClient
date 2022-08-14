package com.rinatvasilev.httpclient

enum class Screens {
    MAIN, DETAILS;

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route?.substringBefore("/")) {
                MAIN.name -> MAIN
                DETAILS.name -> DETAILS
                else -> MAIN
            }
    }
}
