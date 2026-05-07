package com.example.letssopt.data.repository.api

import com.example.letssopt.data.model.home.MainHomeItem
import com.example.letssopt.data.repository.impl.HomeRepositoryImpl


interface HomeRepository {
    fun getHomeItems(): List<MainHomeItem>

    companion object {
        private val _instance: HomeRepository by lazy { HomeRepositoryImpl() }
        fun getInstance(): HomeRepository = _instance
    }
}

