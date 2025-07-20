package com.menene.noteit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.menene.noteit.util.DataStoreManager
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun LandingScreenUi(
    dataStoreManager: DataStoreManager = koinInject(),
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pagerState = rememberPagerState(
            pageCount = { 2 },
        )
        val coroutineScope = rememberCoroutineScope()

        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> {
                    Text("Chcialbys miec wszystkie notatki w jednym miejscu?")
                }
                1 -> {
                    Text("Wlasnie tutaj to bedziesz miec!")
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                dataStoreManager.setHasSeenLandingScreen(true)
                            }
                            onStartClick()
                        }
                    ) {
                        Text("Zacznij teraz!")
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount){ index ->
                val color = if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                        .clickable(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        })
                ) {  }
            }
        }
    }
}