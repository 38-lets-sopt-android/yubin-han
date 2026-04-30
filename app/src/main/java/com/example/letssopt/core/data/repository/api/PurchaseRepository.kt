package com.example.letssopt.core.data.repository.api

import com.example.letssopt.core.data.model.purchase.PurchaseContent
import com.example.letssopt.core.data.repository.impl.PurchaseRepositoryImpl

interface PurchaseRepository {
    fun getPurchaseItems(): List<PurchaseContent>

    companion object {
        private val _instance: PurchaseRepository by lazy { PurchaseRepositoryImpl() }
        fun getInstance(): PurchaseRepository = _instance
    }
}
