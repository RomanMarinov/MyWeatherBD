package com.dev_marinov.myweather.data.remote.dto

import com.dev_marinov.myweather.domain.Condition
import com.google.gson.annotations.SerializedName

data class ConditionDTO(
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: String,
) {
    fun mapToDomain(): Condition {
        return Condition(
            text = text,
            icon = icon
        )
    }
}