package com.menene.noteit.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreenUi(
    onAddNoteClicked: () -> Unit,
    noteViewModel: NoteViewModel
) {
    val notes by noteViewModel.allNotes.collectAsStateWithLifecycle()
    val cellConfiguration = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 150.dp)
    } else {
        StaggeredGridCells.Fixed(count = 3)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddNoteClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = cellConfiguration,
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
        ) {
            items(notes) { note ->
                NoteItem(note)
            }
        }
    }
}