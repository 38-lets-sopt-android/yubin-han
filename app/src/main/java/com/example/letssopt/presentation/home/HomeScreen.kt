package com.example.letssopt.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.model.MainHomeItem
import com.example.letssopt.presentation.home.component.AlgorithmSection
import com.example.letssopt.presentation.home.component.PartySection
import com.example.letssopt.presentation.home.component.TopActionBar
import com.example.letssopt.presentation.home.component.TopBannerSection
import com.example.letssopt.presentation.home.component.UpComingSection


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
        item {
            Spacer(modifier = Modifier.height(23.dp))
            TopActionBar()
            Spacer(modifier = Modifier.height(47.dp))
        }
        items(viewModel.homeItems) { item ->
            when (item) {
                is MainHomeItem.TopBanner -> {
                    TopBannerSection(item.banners)
                    Spacer(modifier = Modifier.height(24.dp))
                }

                is MainHomeItem.AlgorithmSection -> {
                    AlgorithmSection(
                        contents = item.contents
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }

                is MainHomeItem.UpcomingSection -> {
                    UpComingSection(
                        contents = item.contents
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                }

                is MainHomeItem.PartySection -> {
                    PartySection(item.parties)
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
