package com.example.letssopt.presentation.storage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.presentation.storage.component.StorageItemCard

class StorageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StorageScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StorageScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageViewModel = StorageViewModel(),
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
        if (viewModel.storageItems.isEmpty()) {
            Box(
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
                items(viewModel.storageItems) { item ->
                    StorageItemCard(
                        imageRes = item.image,
                        onDeleteClick = {
                            viewModel.removeItem(item)
                        }
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
        StorageScreen()
    }
}
