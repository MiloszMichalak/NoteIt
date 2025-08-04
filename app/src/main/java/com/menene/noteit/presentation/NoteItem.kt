package com.menene.noteit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteItem(note: NoteUI) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(note.title)
        Text(note.description)
        Text(note.date)
    }
}