package com.example.letssopt.presentation.purchase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PurchaseRoute(
    paddingValues: PaddingValues
) {
    PurchaseScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun PurchaseScreen(
    paddingValues: PaddingValues
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "개별구매"
        )
    }
}
