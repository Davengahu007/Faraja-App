package com.example.faraja_app

data class Counselor(
    val name: String,
    val profilePictureResId: Int, // Resource ID of the counselor's profile picture
    val lastMessage: String,
    val lastMessageTime: String // You can use a proper time representation here (e.g., Long timestamp)
)

object CounselorData {
    val counselorList = listOf(
        Counselor(
            name = "Dummy",
            profilePictureResId = R.drawable.mia,
            lastMessage = "Dummy",
            lastMessageTime = "dummy"
        ),
        Counselor(
            name = "Counselor Mia",
            profilePictureResId = R.drawable.mia,
            lastMessage = "How has your day been?",
            lastMessageTime = "1:34"
        ),
        Counselor(
            name = "Counselor Odhiambo",
            profilePictureResId = R.drawable.odhis,
            lastMessage = "Feeling better today?",
            lastMessageTime = "09:12"
        ),
        Counselor(
            name = "Counselor Quandale",
            profilePictureResId = R.drawable.quandale,
            lastMessage = "Hello! How can I help you?",
            lastMessageTime = "12:18"
        ),
        // Add more counselors as needed
    )
}
