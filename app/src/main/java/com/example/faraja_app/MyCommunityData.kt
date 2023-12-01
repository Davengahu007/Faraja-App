package com.example.faraja_app

data class MyCommunity(val name: String, val members: String, val description: String)

object MyCommunityData {
    val MycommunityList = listOf(
        MyCommunity(
            "Worriors",
            "134",
            "This is a humble community of individuals who share a common passion for learning and growth."
        ),
        MyCommunity(
            "NewLife",
            "31",
            "Collaboration, and the exchange of ideas. Whether you're a seasoned expert or a newcomer eager to explore, everyone's perspective is valued and respected."
        ),
        MyCommunity(
            "PathFinders",
            "94",
            "In this welcoming space, we encourage open dialogue, collaboration, and the exchange of ideas. Whether you're a seasoned expert or a newcomer eager to explore, everyone's perspective is valued and respected."
        ),
        MyCommunity(
            "SuicideNoMore",
            "122",
            "This is a humble community of individuals who share a common passion for learning and growth. Whether you're a seasoned expert or a newcomer eager to explore, everyone's perspective is valued and respected."
        ),
    )
}