package com.example.letssopt.presentation.purchase

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.data.local.AppDatabase
import com.example.letssopt.core.data.model.purchase.PurchaseContent
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.purchase.component.PurchaseItemCard

@Composable
fun PurchaseRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val dao = database.storedItemDao()

    val viewModel: PurchaseViewModel = viewModel(
        factory = PurchaseViewModelFactory(dao)
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is PurchaseContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    PurchaseScreen(
        modifier = modifier.padding(paddingValues),
        purchaseItems = uiState.purchaseItems,
        onStoreClick = { item -> viewModel.storeItem(item) }
    )
}

@Composable
fun PurchaseScreen(
    purchaseItems: List<PurchaseContent>,
    onStoreClick: (PurchaseContent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "개별 구매",
            style = WatchaTheme.typography.headline.head2B20,
            color = WatchaTheme.colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp),
        )

        Spacer(modifier = Modifier.height(45.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(purchaseItems, key = { it.title }) { item ->
                PurchaseItemCard(
                    imageRes = item.image,
                    onStoreClick = { onStoreClick(item) },
                    contentTitle = item.title,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PurchaseScreenPreview() {
    LETSSOPTTheme {
        PurchaseScreen(
            purchaseItems = listOf(
                PurchaseContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
                PurchaseContent("기묘한 이야기", R.drawable.img_poster_stranger_things),
            ),
            onStoreClick = {}
        )
    }
}
