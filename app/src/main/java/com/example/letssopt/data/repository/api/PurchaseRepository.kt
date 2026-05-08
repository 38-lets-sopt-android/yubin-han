package com.example.letssopt.data.repository.api

import com.example.letssopt.data.model.purchase.PurchaseContent
import com.example.letssopt.data.repository.impl.PurchaseRepositoryImpl


interface PurchaseRepository {
    fun getPurchaseItems(): List<PurchaseContent>

    companion object {
        private val _instance: PurchaseRepository by lazy { PurchaseRepositoryImpl() }
        fun getInstance(): PurchaseRepository = _instance
    }
}
