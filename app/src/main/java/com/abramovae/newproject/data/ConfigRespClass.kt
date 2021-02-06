package com.abramovae.newproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ConfigRespClass(
    @SerialName("images")
    val images: List<ImageResp>) {
}