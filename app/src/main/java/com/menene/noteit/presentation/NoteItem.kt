package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.menene.noteit.data.Note

@Composable
fun NoteItem(note: Note) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(note.title)
        Text(note.description)
        Text(note.timestamp.toString())
    }
}