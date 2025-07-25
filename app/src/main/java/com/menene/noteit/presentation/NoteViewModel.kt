package com.menene.noteit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menene.noteit.data.Note
import com.menene.noteit.domain.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteDao: NoteDao
): ViewModel() {
    private val _allNotes = MutableStateFlow(emptyList<Note>())
//        .onStart { noteDao.getAllNotes() }

    init {
        viewModelScope.launch {
            _allNotes.value =  noteDao.getAllNotes()
        }
    }

    val allNotes = _allNotes.asStateFlow()

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