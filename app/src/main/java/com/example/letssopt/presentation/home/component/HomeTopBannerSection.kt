package com.example.letssopt.presentation.home.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.letssopt.R
import com.example.letssopt.core.data.model.home.MainHomeItem
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import kotlin.math.absoluteValue

@Composable
fun HomeTopBannerSection(
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
            modifier = Modifier.padding(start = 19.dp, top = 24.dp),
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
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
        ) { page ->
            val actualIndex = page % banners.size
            val banner = banners[actualIndex]
            var isPressed by remember { mutableStateOf(false) }
            val pressScale by animateFloatAsState(
                targetValue = if (isPressed) 0.97f else 1f
            )

            Image(
                painter = painterResource(id = banner.image),
                contentDescription = banner.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(7f / 4f)
                    .graphicsLayer {
                        val pageOffset = pagerState.getOffsetDistanceInPages(page).absoluteValue
                        alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                        val swipeScale = lerp(0.95f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                        scaleX = swipeScale * pressScale
                        scaleY = swipeScale * pressScale
                        clip = true
                        shape = RoundedCornerShape(10.dp)

                    }
                    .pointerInput(banner.title) {
                        detectTapGestures(
                            onPress = {
                                isPressed = true
                                tryAwaitRelease()
                                isPressed = false
                            }
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
        HomeTopBannerSection(
            banners = listOf(
                MainHomeItem.Contents("메니페스트", R.drawable.img_poster_manifest),
                MainHomeItem.Contents("크라임씬", R.drawable.img_poster_crime_scene),
                MainHomeItem.Contents("폭싹 속았수다", R.drawable.img_poster_jeju_love),
            )
        )
    }
}
