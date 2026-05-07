package com.example.letssopt.core.data.model.profile

data class UserProfile(
    val id: String,
    val name: String,
    val email: String = "",
    val age: Int = 0,
    val part: String
)
