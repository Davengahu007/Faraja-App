package com.example.faraja_app

data class Counselor(
    val id: String,
    val name: String,
    val profilePictureResId: Int, // Resource ID of the counselor's profile picture
    val lastMessage: String,
    val lastMessageTime: String, // You can use a proper time representation here (e.g., Long timestamp)
    val chatMessages: List<ChatMessage>
)

object CounselorData {
    val counselorList = listOf(
        Counselor(
            id = "1",
            name = "Dummy",
            profilePictureResId = R.drawable.mia,
            lastMessage = "Dummy",
            lastMessageTime = "dummy",
            chatMessages = listOf(
                ChatMessage("1", "Hello!", 1638561853000), // Sample chat messages
                ChatMessage("2", "Hi there!", 1638561898000)
                // Add more chat messages as needed
            )
        ),
        Counselor(
            id = "2",
            name = "Counselor Mia",
            profilePictureResId = R.drawable.mia,
            lastMessage = "How has your day been?",
            lastMessageTime = "1:34",
            chatMessages = listOf(
                ChatMessage("1", "Hello!", 1638561853000), // Sample chat messages
                ChatMessage("2", "Hi there!", 1638561898000)
                // Add more chat messages as needed
            )
        ),
        Counselor(
            id = "3",
            name = "Counselor Odhiambo",
            profilePictureResId = R.drawable.odhis,
            lastMessage = "Feeling better today?",
            lastMessageTime = "09:12",
            chatMessages = listOf(
                ChatMessage("1", "Hello!", 1638561853000), // Sample chat messages
                ChatMessage("2", "Hi there!", 1638561898000)
                // Add more chat messages as needed
            )
        ),
        Counselor(
            id = "4",
            name = "Counselor Quandale",
            profilePictureResId = R.drawable.quandale,
            lastMessage = "Hello! How can I help you?",
            lastMessageTime = "12:18",
            chatMessages = listOf(
                ChatMessage("1", "Hello!", 1638561853000), // Sample chat messages
                ChatMessage("2", "Hi there!", 1638561898000)
                // Add more chat messages as needed
            )
        ),
        // Add more counselors as needed
    )
}
