package com.menene.noteit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.menene.noteit.presentation.NoteUI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "notes")
data class Note @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val timestamp: Long  = Clock.System.now().epochSeconds
)

fun Note.toNoteUI(): NoteUI {
    return NoteUI(
        title = title,
        description = description,
        date = timestamp.toUiDate()
    )
}

fun Long.toUiDate(): String {
    val date = Date(this * 1000)
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(date)
}




