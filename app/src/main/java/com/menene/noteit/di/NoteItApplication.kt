package com.menene.noteit.di

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NoteItApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
           module { mainModule }
        }
    }
}