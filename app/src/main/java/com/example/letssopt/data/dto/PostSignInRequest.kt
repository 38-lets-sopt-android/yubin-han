package com.example.letssopt.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PostSignInRequest(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
)
