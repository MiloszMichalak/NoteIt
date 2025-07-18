package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LandingScreenUi() {
    Column {
        val pagerState = rememberPagerState(
            pageCount = { 2 },
            initialPage = 0
        )

        HorizontalPager(
            state = pagerState,
        ) { page ->
            Text(
                text = "Page: $page",
            )
        }
    }
}