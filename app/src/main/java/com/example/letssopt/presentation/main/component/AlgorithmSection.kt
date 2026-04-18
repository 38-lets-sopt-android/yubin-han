package com.example.letssopt.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.model.MainHomeItem

@Composable
fun AlgorithmSection(
    contents: List<MainHomeItem.Contents>,
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
            Column {
                Icon(
                    ImageVector.vectorResource(R.drawable.ic_watcha_algorithm),
                    null,
                    tint = WatchaTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "예능부터 드라마까지",
                    style = WatchaTheme.typography.cap.captionR20,
                    color = WatchaTheme.colors.textSecondary,
                )
            }
            Text(
                text = "더보기",
                style = WatchaTheme.typography.body.bodyR12,
                color = WatchaTheme.colors.textSecondary,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp))
        {
            items(contents) { content ->
                Image(
                    painter = painterResource(id = content.image),
                    contentDescription = content.title,
                    modifier = Modifier
                        .width(110.dp)
                        .aspectRatio(11 / 16f)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
private fun AlgorithmSectionPreview() {
    LETSSOPTTheme {
        AlgorithmSection(
            contents =
                listOf(
                    MainHomeItem.Contents("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
                    MainHomeItem.Contents("기묘한 이야기", R.drawable.img_poster_stranger_things),
                    MainHomeItem.Contents("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
                )
        )
    }
}
