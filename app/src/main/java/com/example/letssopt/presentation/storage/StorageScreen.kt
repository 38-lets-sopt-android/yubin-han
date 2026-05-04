package com.example.letssopt.presentation.storage

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.local.AppDatabase
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.storage.component.StorageItemCard


@Composable
fun StorageRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).storedItemDao()
    val viewModel: StorageViewModel = viewModel(factory = StorageViewModelFactory(dao))
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is StorageContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    StorageScreen(
        storedItems = uiState.storedItems,
        onDeleteClick = { item -> viewModel.deleteItem(item) },
        modifier = modifier.padding(paddingValues),
    )
}


@Composable
fun StorageScreen(
    storedItems: List<StoredItemEntity>,
    onDeleteClick: (StoredItemEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = "찜한 목록",
            style = WatchaTheme.typography.headline.head2B20,
            color = WatchaTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp),
        )

        Spacer(modifier = Modifier.height(45.dp))
        if (storedItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "찜해놓은 목록이 없습니다.",
                    style = WatchaTheme.typography.headline.head2B20,
                    color = WatchaTheme.colors.textPrimary,
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(11.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items(storedItems, key = { it.id }) { item ->
                    StorageItemCard(
                        imageRes = item.imageRes,
                        contentTitle = item.title,
                        onDeleteClick = { onDeleteClick(item) },
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
            storedItems = emptyList(),
            onDeleteClick = {},
        )
    }
}
