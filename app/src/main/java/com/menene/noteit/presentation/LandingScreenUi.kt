package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.menene.noteit.R
import com.menene.noteit.presentation.components.PagerDots

@Composable
fun LandingScreenUi(
    onStartClick: () -> Unit
) {
    Scaffold { innerPadding ->
        val pagerState = rememberPagerState(
            pageCount = { 2 },
        )
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .padding(horizontal = 32.dp)
                .fillMaxSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    when (page) {
                        0 -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(32.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(96.dp),
                                    imageVector = Icons.Outlined.EditNote,
                                    contentDescription = stringResource(R.string.noteit_icon),
                                )
                                Text(
                                    text = stringResource(R.string.all_in_one_place),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        1 -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(32.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.you_will_have_it),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Button(
                                    onClick = {
                                        onStartClick()
                                    }
                                ) {
                                    Text(
                                        text = stringResource(R.string.start_now)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Column {
                PagerDots(
                    pagerState = pagerState,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}