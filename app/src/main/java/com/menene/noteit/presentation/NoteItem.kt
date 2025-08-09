package com.menene.noteit.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(note: NoteUI) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant, MaterialTheme.shapes.medium)
            .padding(4.dp)
            .widthIn(min = 128.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = note.title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(text = note.description)
        Text(text =note.date)
    }
}