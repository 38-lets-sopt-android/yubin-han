package com.example.letssopt.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.data.model.MainHomeItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.presentation.home.component.HomeAlgorithmSection
import com.example.letssopt.presentation.home.component.HomePartySection
import com.example.letssopt.presentation.home.component.HomeTopBannerSection
import com.example.letssopt.presentation.home.component.HomeUpComingSection


@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    HomeScreen(
        modifier = Modifier.padding(paddingValues)
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = HomeViewModel(),
) {
    LazyColumn(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
    ) {
        items(viewModel.homeItems) { item ->
            when (item) {
                is MainHomeItem.TopBanner -> {
                    HomeTopBannerSection(item.banners)
                    Spacer(modifier = Modifier.height(24.dp))
                }

                is MainHomeItem.AlgorithmSection -> {
                    HomeAlgorithmSection(contents = item.contents)
                    Spacer(modifier = Modifier.height(26.dp))
                }

                is MainHomeItem.UpcomingSection -> {
                    HomeUpComingSection(contents = item.contents)
                    Spacer(modifier = Modifier.height(26.dp))
                }

                is MainHomeItem.PartySection -> {
                    HomePartySection(item.parties)
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    LETSSOPTTheme {
        HomeScreen()
    }
}
