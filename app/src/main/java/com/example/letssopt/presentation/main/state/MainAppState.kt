package com.example.letssopt.presentation.main.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.navigation.Route
import com.example.letssopt.presentation.home.navigation.Home
import com.example.letssopt.presentation.home.navigation.navigateToHome
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.onboarding.login.navigation.Login
import com.example.letssopt.presentation.purchase.navigation.navigateToPurchase
import com.example.letssopt.presentation.search.navigation.navigateToSearch
import com.example.letssopt.presentation.storage.navigation.navigateToStorage
import com.example.letssopt.presentation.webtoon.navigation.navigateToWebtoon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val startDestination: Route = if (AuthRepository.getInstance().getLoggedIn()) Home else Login

    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )


    val isBottomBarVisible: StateFlow<Boolean> = currentDestination
        .map { destination ->
            MainTab.contains { tab ->
                destination?.hasRoute(tab::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions = navOptions)
            MainTab.PURCHASE -> navController.navigateToPurchase(navOptions = navOptions)
            MainTab.WEBTOON -> navController.navigateToWebtoon(navOptions = navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions = navOptions)
            MainTab.STORAGE -> navController.navigateToStorage(navOptions = navOptions)
        }
    }

}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MainAppState = remember(navController, coroutineScope) {
    MainAppState(navController, coroutineScope)
}
