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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.data.model.MainHomeItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.presentation.home.component.HomeAlgorithmSection
import com.example.letssopt.presentation.home.component.HomePartySection
import com.example.letssopt.presentation.home.component.HomeTopBannerSection
import com.example.letssopt.presentation.home.component.HomeUpComingSection

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = uiState,
        modifier = Modifier.padding(paddingValues)
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.UiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
    ) {
        items(state.homeItems) { item ->
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
        HomeScreen(
            state = HomeContract.UiState(
                homeItems = listOf(
                    MainHomeItem.TopBanner(
                        banners = listOf(
                            MainHomeItem.Contents("메니페스트", R.drawable.img_poster_manifest),
                            MainHomeItem.Contents("크라임씬", R.drawable.img_poster_crime_scene),
                            MainHomeItem.Contents("폭싹 속았수다", R.drawable.img_poster_jeju_love),
                        )
                    ),
                    MainHomeItem.AlgorithmSection(
                        contents = listOf(
                            MainHomeItem.Contents("기묘한 이야기", R.drawable.img_poster_stranger_things),
                            MainHomeItem.Contents("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
                        ),
                    ),
                    MainHomeItem.UpcomingSection(
                        contents = listOf(
                            MainHomeItem.Contents("기묘한 이야기", R.drawable.img_poster_stranger_things),
                            MainHomeItem.Contents("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
                        )
                    ),
                    MainHomeItem.PartySection(
                        parties = listOf(
                            MainHomeItem.PartyContent(
                                "왕과 사는 남자",
                                "오늘 21:13에 시작",
                                R.drawable.img_poster_king_man,
                            )
                        )
                    )
                )
            )
        )
    }
}
