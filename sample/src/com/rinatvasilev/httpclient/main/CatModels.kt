package com.rinatvasilev.httpclient.main

import com.google.gson.annotations.SerializedName

data class CatInfo(val id: String, val tags: List<String>)

data class CatResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("created_at")
    val createTime: String,

    @SerializedName("tags")
    val tags: List<String>
) {
    fun toCatInfo() = CatInfo(id = id, tags = tags)
}
