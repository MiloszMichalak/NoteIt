package com.menene.noteit.di

import android.app.Application
import androidx.room.Room
import com.menene.noteit.data.NoteDatabase
import com.menene.noteit.presentation.NoteViewModel
import com.menene.noteit.util.DataStoreManager
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    singleOf(::DataStoreManager)

    viewModelOf(::NoteViewModel)

    single {
        Room.databaseBuilder(
            get<Application>().applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    single {
        get<NoteDatabase>().noteDao()
    }
}