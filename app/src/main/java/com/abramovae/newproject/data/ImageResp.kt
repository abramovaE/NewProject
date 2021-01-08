package com.abramovae.newproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResp(
    @SerialName("base_url")
    val baseUrl: String) {
}