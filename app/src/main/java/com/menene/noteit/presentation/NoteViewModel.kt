package com.menene.noteit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menene.noteit.data.Note
import com.menene.noteit.data.toNoteUI
import com.menene.noteit.domain.NoteDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteDao: NoteDao
): ViewModel() {
    val allNotes = noteDao.getAllNotes()
        .map { notes -> notes.map { note -> note.toNoteUI() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
    )

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }
}