package com.example.letssopt.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.home.component.HomeTopActionBar
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.purchase.PurchaseRoute
import com.example.letssopt.presentation.search.SearchRoute
import com.example.letssopt.presentation.storage.StorageRoute
import com.example.letssopt.presentation.webtoon.WebtoonRoute
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var currentTab by remember { mutableStateOf(MainTab.HOME) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        bottomBar = {
            MainBottomBar(
                isVisible = true,
                tabs = MainTab.entries.toImmutableList(),
                currentTab = currentTab,
                onTabSelected = { currentTab = it }
            )
        },
        topBar = {
            when (currentTab) {
                MainTab.HOME -> HomeTopActionBar()
                else -> {}
            }
        },

        ) { innerPadding ->
        when (currentTab) {
            MainTab.HOME -> HomeRoute(paddingValues = innerPadding)
            MainTab.PURCHASE -> PurchaseRoute(paddingValues = innerPadding)
            MainTab.WEBTOON -> WebtoonRoute(paddingValues = innerPadding)
            MainTab.SEARCH -> SearchRoute(paddingValues = innerPadding)
            MainTab.STORAGE-> StorageRoute(paddingValues = innerPadding)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    LETSSOPTTheme {
        MainScreen()
    }
}
