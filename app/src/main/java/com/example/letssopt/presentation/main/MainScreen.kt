package com.example.letssopt.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.presentation.home.navigation.homeGraph
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.main.state.MainAppState
import com.example.letssopt.presentation.onboarding.login.navigation.loginGraph
import com.example.letssopt.presentation.onboarding.signup.signUpGraph
import com.example.letssopt.presentation.purchase.navigation.purchaseGraph
import com.example.letssopt.presentation.search.navigation.searchGraph
import com.example.letssopt.presentation.storage.navigation.storageGraph
import com.example.letssopt.presentation.webtoon.navigation.webtoonGraph
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    appState: MainAppState,
    modifier: Modifier = Modifier
) {
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = WatchaTheme.colors.backGround,
        bottomBar = {
            MainBottomBar(
                isVisible = isBottomBarVisible,
                tabs = MainTab.entries.toImmutableList(),
                currentTab = currentTab ?: MainTab.HOME,
                onTabSelected = { appState.navigate(it) }
            )

        },
    ) { innerPadding ->
        MainNavHost(
            appState = appState,
            innerPadding = innerPadding,
        )
    }
}


@Composable
private fun MainNavHost(
    appState: MainAppState,
    innerPadding: PaddingValues,
) {
    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = appState.navController,
        startDestination = appState.startDestination,
    ) {
        loginGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        signUpGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        homeGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        purchaseGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        searchGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        storageGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )
        webtoonGraph(
            navController = appState.navController,
            innerPadding = innerPadding,
        )

    }
}
