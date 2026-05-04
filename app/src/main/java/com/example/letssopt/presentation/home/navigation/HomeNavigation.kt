package com.example.letssopt.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.home.myprofile.navigateToMyProfile
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(
    navOptions: NavOptions
) = navigate(Home, navOptions)

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Home> {
        HomeRoute(
            paddingValues = innerPadding,
            navigateToMyProfile = navController::navigateToMyProfile,
        )
    }
}


@Serializable
data object Home : MainTabRoute
