package com.example.letssopt.presentation.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.data.model.StorageContent
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.presentation.storage.component.StorageItemCard


@Composable
fun StorageRoute(
    paddingValues: PaddingValues,
    viewModel: StorageViewModel = viewModel(),
) {
    val storageItems = viewModel.storageItems

    StorageScreen(
        modifier = Modifier.padding(paddingValues),
        items = storageItems,
        onDeleteClick = { item -> viewModel.removeItem(item) }
    )
}


@Composable
fun StorageScreen(
    items: List<StorageContent>,
    onDeleteClick: (StorageContent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "찜한 목록",
            style = WatchaTheme.typography.headline.head2B20,
            color = WatchaTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp)

        )

        Spacer(modifier = Modifier.height(45.dp))
        if (items.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "찜해놓은 목록이 없습니다.",
                    style = WatchaTheme.typography.headline.head2B20,
                    color = WatchaTheme.colors.primaryRed,
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(11.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items(items) { item ->
                    StorageItemCard(
                        imageRes = item.image,
                        onDeleteClick = { onDeleteClick(item) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StorageScreenPreview() {
    LETSSOPTTheme {
        StorageScreen(
            items = listOf(
                StorageContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
                StorageContent("기묘한 이야기", R.drawable.img_poster_stranger_things),
            ),
            onDeleteClick = {}
        )
    }
}
