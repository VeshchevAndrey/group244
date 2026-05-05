package com.example.application244

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val title: String,
    val status: Boolean = false
)
