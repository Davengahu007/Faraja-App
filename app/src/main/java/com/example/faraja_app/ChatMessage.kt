package com.example.faraja_app

data class ChatMessage(
    val senderId: String,
    val message: String,
    val timestamp: Long // You can use a proper time representation here (e.g., Long timestamp)
)
