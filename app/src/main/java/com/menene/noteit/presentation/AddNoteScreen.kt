package com.menene.noteit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.menene.noteit.R

@Composable
fun AddNoteScreen(
    noteViewModel: NoteViewModel,
    onNoteAdded: () -> Unit = {}
) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.error)
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(R.string.delete_note),
                    )
                }

                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.inversePrimary)
                            .padding(8.dp),
                        imageVector = Icons.Outlined.Save,
                        contentDescription = stringResource(R.string.save_note),
                    )
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                shape = MaterialTheme.shapes.large,
                placeholder = { Text(text = stringResource(R.string.title)) },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                shape = MaterialTheme.shapes.large,
                placeholder = { Text(text = stringResource(R.string.description)) },
                modifier = Modifier
                    .fillMaxWidth(),
                minLines = 6
            )
        }
    }
}