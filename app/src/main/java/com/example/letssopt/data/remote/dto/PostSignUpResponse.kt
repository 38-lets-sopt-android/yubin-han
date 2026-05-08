package com.example.letssopt.data.remote.dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostSignUpResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
)
