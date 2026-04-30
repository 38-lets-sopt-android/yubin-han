package com.example.letssopt.core.data.repository.api

import com.example.letssopt.core.data.model.purchase.PurchaseContent

interface PurchaseRepository {
    fun getPurchaseItems(): List<PurchaseContent>
}
