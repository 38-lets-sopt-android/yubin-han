package com.example.letssopt.core.data.model

sealed class MainHomeItem {
    data class Contents(
        val title: String,
        val image: Int,
    )

    data class TopBanner(
        val banners: List<Contents>
    ) : MainHomeItem()

    data class AlgorithmSection(
        val contents: List<Contents>
    ) : MainHomeItem()

    data class UpcomingSection(
        val contents: List<Contents>
    ) : MainHomeItem()


    data class PartySection(
        val parties: List<PartyContent>
    ) : MainHomeItem()

    data class PartyContent(
        val title: String,
        val startTime: String,
        val image: Int
    )
}

data class StorageContent(
    val title: String,
    val image: Int,
)
