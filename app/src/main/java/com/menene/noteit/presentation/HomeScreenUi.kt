package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreenUi(
    onAddNoteClicked: () -> Unit,
    noteViewModel: NoteViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val notes by noteViewModel.allNotes.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier.wrapContentSize()
        ) {
            items(notes) { note ->
                NoteItem(note)
            }
        }

        Button(
            onClick = {
                onAddNoteClicked()
            }
        ) {
            Text("Add Note")
        }
    }
}