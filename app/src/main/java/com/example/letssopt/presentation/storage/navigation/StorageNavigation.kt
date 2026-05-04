package com.example.letssopt.presentation.storage.navigation
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.presentation.storage.StorageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToStorage(navOptions: NavOptions) = navigate(Storage, navOptions)

fun NavGraphBuilder.storageGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Storage> {
        StorageRoute(
            paddingValues = innerPadding,
        )
    }
}


@Serializable
data object Storage : MainTabRoute
