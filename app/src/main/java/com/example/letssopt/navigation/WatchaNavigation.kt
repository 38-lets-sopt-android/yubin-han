package com.example.letssopt.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data object Main : MainTabRoute {
    fun serializer(): KSerializer<Main> {
        return serializer()
    }
}

@Serializable
data object Purchase : MainTabRoute {
    fun serializer(): KSerializer<Purchase> {
        return serializer()
    }
}

@Serializable
data object Webtoon : MainTabRoute {
    fun serializer(): KSerializer<Webtoon> {
        return serializer()
    }
}

@Serializable
data object Search : MainTabRoute {
    fun serializer(): KSerializer<Search> {
        return serializer()
    }
}

@Serializable
data object Storage : MainTabRoute {
    fun serializer(): KSerializer<Storage> {
        return serializer()
    }
}
