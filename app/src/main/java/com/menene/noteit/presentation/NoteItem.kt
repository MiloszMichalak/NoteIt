package com.menene.noteit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(note: NoteUI) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .background(
                color =MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium)
            .padding(4.dp)
    ) {
        Text(note.title)
        Text(note.description)
        Text(note.date)
    }
}


@Preview
@Composable fun NoteItemPreview() {
    NoteItem(
        note = NoteUI(
            title = "Sample Note",
            description = "This is a sample note description.",
            date = "2023-10-01"
        )
    )
}