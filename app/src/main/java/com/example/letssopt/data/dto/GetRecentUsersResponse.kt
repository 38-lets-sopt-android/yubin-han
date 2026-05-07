package com.example.letssopt.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRecentUsersResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: RecentUsersDataList? = null,
)

@Serializable
data class RecentUsersDataList(
    @SerialName("users")
    val users: List<RecentUsersData>
)

@Serializable
data class RecentUsersData(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("part")
    val part: String,
)
