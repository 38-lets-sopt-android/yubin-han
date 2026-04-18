package com.example.letssopt.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.model.MainHomeItem
import kotlin.math.absoluteValue

@Composable
fun TopBannerSection(
    banners: List<MainHomeItem.Contents>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % banners.size),
        pageCount = { if (banners.isEmpty()) 0 else Int.MAX_VALUE }
    ),
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "방금 막 도착한 신상 컨텐츠",
            style = WatchaTheme.typography.headline.head2B20,
            color = WatchaTheme.colors.textPrimary,
            modifier = Modifier.padding(start = 19.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "예능부터 드라마까지!",
            style = WatchaTheme.typography.cap.captionR18,
            color = WatchaTheme.colors.textSecondary,
            modifier = Modifier.padding(start = 19.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (banners.isEmpty()) return
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp),
            pageSpacing = 16.dp,
        ) { page ->
            val actualIndex = page % banners.size
            val banner = banners[actualIndex]

            Image(
                painter = painterResource(id = banner.image),
                contentDescription = banner.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .graphicsLayer {
                        val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            )
        }
    }
}

@Preview
@Composable
private fun TopBannerSectionPreview() {
    LETSSOPTTheme {
        TopBannerSection(
            banners = listOf(
                MainHomeItem.Contents("메니페스트", R.drawable.img_poster_manifest),
                MainHomeItem.Contents("크라임씬", R.drawable.img_poster_crime_scene),
                MainHomeItem.Contents("폭싹 속았수다", R.drawable.img_poster_jeju_love),
            )
        )
    }
}
