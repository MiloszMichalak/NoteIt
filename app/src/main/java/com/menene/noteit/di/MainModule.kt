package com.menene.noteit.di

import android.app.Application
import androidx.room.Room
import com.menene.noteit.data.NoteDatabase
import com.menene.noteit.domain.NoteDao
import com.menene.noteit.presentation.NoteViewModel
import com.menene.noteit.util.DataStoreManager
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

@Module
class MainModule {

    @Single
    fun provideDataStoreManager(application: Application): DataStoreManager {
        return DataStoreManager(application.applicationContext)
    }

    @Single
    fun provideNoteViewmodel(noteDao: NoteDao): NoteViewModel {
        return NoteViewModel(noteDao)
    }

    @Single
    fun provideRoomDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Single
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
}