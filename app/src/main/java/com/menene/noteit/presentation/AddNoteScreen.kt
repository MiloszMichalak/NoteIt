package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.menene.noteit.data.Note

@Composable
fun AddNoteScreen(
    noteViewModel: NoteViewModel,
    onNoteAdded: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }

        TextField(
            value = title,
            onValueChange = { title = it },
        )
        TextField(
            value = description,
            onValueChange = { description = it },
        )
        Button(
            onClick = {
                noteViewModel.insertNote(
                    Note(title = title, description = description)
                )
                onNoteAdded()
            }
        ) {
            Text("Save Note")
        }
    }
}