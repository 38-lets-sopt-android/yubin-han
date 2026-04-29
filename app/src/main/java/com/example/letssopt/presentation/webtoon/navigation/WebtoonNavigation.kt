package com.example.letssopt.presentation.webtoon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.presentation.webtoon.WebtoonRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToWebtoon(navOptions: NavOptions) = navigate(Webtoon, navOptions)

fun NavGraphBuilder.webtoonGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Webtoon> {
        WebtoonRoute(
            paddingValues = innerPadding,
        )
    }
}


@Serializable
data object Webtoon : MainTabRoute
