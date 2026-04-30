package com.example.letssopt.core.data.repository.api

import com.example.letssopt.core.data.model.home.MainHomeItem

interface HomeRepository {
    fun getHomeItems(): List<MainHomeItem>
}

