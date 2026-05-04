package com.example.letssopt.presentation.purchase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.presentation.purchase.PurchaseRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToPurchase(navOptions: NavOptions) = navigate(Purchase, navOptions)

fun NavGraphBuilder.purchaseGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Purchase> {
        PurchaseRoute(
            paddingValues = innerPadding,
        )
    }
}


@Serializable
data object Purchase: MainTabRoute
