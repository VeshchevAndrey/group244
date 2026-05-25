package com.example.application244

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val name: String = "",
    val phone: String = "",
    val email: String = ""
)
