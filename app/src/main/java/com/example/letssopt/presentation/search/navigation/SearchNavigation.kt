package com.example.letssopt.presentation.search.navigation
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.presentation.search.SearchRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSearch(navOptions: NavOptions) = navigate(Search, navOptions)

fun NavGraphBuilder.searchGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Search> {
        SearchRoute(
            paddingValues = innerPadding,
        )
    }
}


@Serializable
data object Search : MainTabRoute
