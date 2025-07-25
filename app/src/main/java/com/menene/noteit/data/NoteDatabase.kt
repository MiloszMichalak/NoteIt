package com.menene.noteit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.menene.noteit.domain.NoteDao

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}