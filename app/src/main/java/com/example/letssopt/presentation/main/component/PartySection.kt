package com.example.letssopt.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.model.MainHomeItem


@Composable
fun PartySection(
    parties: List<MainHomeItem.PartyContent>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "왓챠 파티",
                style = WatchaTheme.typography.headline.head2B20,
                color = WatchaTheme.colors.textPrimary
            )
            Text(
                text = "더보기",
                style = WatchaTheme.typography.body.bodyR12,
                color = WatchaTheme.colors.textSecondary,
            )
        }
        Spacer(modifier = Modifier.height(7.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp))
        {
            items(parties) { party ->
                Box(
                    modifier = Modifier
                        .width(196.dp)
                        .aspectRatio(196 / 185f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = WatchaTheme.colors.surface)
                    ) {
                        Image(
                            painter = painterResource(id = party.image),
                            contentDescription = party.movieTitle,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = party.startTime,
                            color = WatchaTheme.colors.primaryRed,
                            style = WatchaTheme.typography.body.bodyR12,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "# ${party.movieTitle}",
                            color = WatchaTheme.colors.textPrimary,
                            style = WatchaTheme.typography.body.bodyR12,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "알림 설정",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 7.dp, end = 5.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PartySectionPreview() {
    LETSSOPTTheme {
        PartySection(
            parties =
                listOf(
                    MainHomeItem.PartyContent(
                        "왕과  사는  남자",
                        "오늘  21 : 13 에  시작",
                        R.drawable.img_poster_king_man
                    ),
                )
        )
    }
}
